<template>
    <div>
        <!-- 面包屑导航 -->
       <el-breadcrumb separator-class="el-icon-arrow-right">
             <el-button type="info" @click="logout">退出</el-button>
        </el-breadcrumb>
        <h2 v-if="type=='user'">欢迎用户登录</h2>
        <!-- 卡片视图区域 -->
        <el-card class="box-card"   v-if="type=='admin'">
            <el-row :gutter="20">
                <el-col :span="4" >
                     <!-- 搜索与添加区域 -->
                    <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getUserList">
                        <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>
                </el-col>
            </el-row>

            <el-table :data="userlist" style="width: 100%" border stripe>
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="username" label="账号" ></el-table-column>
                 <el-table-column prop="password" label="密码" >******</el-table-column>
                 <el-table-column prop="name" label="名字" ></el-table-column>
                <el-table-column prop="email" label="邮箱" ></el-table-column>
                <el-table-column prop="type" label="类型"> </el-table-column>
                <el-table-column :formatter="dataFormat" prop="date" label="日期"></el-table-column>
                <el-table-column  label="状态">
                    <template slot-scope="scope">
                         <el-button type="success" v-if="scope.row.status==0" size="mini">正常</el-button>
                         <el-button type="danger" v-if="scope.row.status==1" size="mini"  @click="userStateChange(scope.row)">异常</el-button>
                        </el-tooltip>
                    </template>
                   
                </el-table-column>
                <el-table-column label="操作" width="180px">
                    <template slot-scope="scope">
                        <!-- 修改按钮 -->
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="showeditDialogVisible(scope.row.id)"></el-button>
                        <!-- 删除按钮 -->
                        <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeUserById(scope.row.id)"></el-button>
                        <el-tooltip effect="dark" content="更改类型" placement="top" :enterable="false">
                             <!-- 分配角色按钮 -->
                                <el-button type="warning" icon="el-icon-setting"  size="mini"  @click="updateType(scope.row)"></el-button>
                        </el-tooltip>
                    
                    </template>
                </el-table-column>
            </el-table>

            <!-- 底部分页功能 -->
                <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="queryInfo.pagenum"
                :page-sizes="[1, 2, 5, 10]"
                :page-size="queryInfo.pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
                </el-pagination>

        </el-card>

        <!-- 添加用户的对话框 -->
        <el-dialog
        title="添加用户"
        :visible.sync="addDialogVisible"
        width="50%">
        <!-- 内容主题区域 -->
        <span>
            <el-form ref="addform" :rules="addformRules" :model="addform" label-width="80px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="addform.username"></el-input>
                </el-form-item>
                 <el-form-item label="密码" prop="password">
                    <el-input v-model="addform.password"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="addform.name"></el-input>
                </el-form-item>
                 <el-form-item label="邮箱" prop="email">
                    <el-input v-model="addform.email"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="type"> 
                    <el-select v-model="addform.type" placeholder="请选择类型">
                    <el-option label="用户" value="user"></el-option>
                    <el-option label="管理员" value="admin"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
        </span>
        <!-- 底部区域 -->
        <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addUser('addform')" >确 定</el-button>
        </span>
        </el-dialog>

         <!-- 修改用户的对话框 -->
        <el-dialog
        title="修改用户"
        :visible.sync="editDialogVisible"
        width="50%">
        <!-- 内容主题区域 -->
        <span>
            <el-form ref="" :model="editForm" label-width="80px">
                <el-form-item label="用户名" >
                    <el-input v-model="editForm.username" :disabled="true"></el-input>
                </el-form-item>
                 <el-form-item label="密码">
                    <el-input v-model="editForm.password"></el-input>
                </el-form-item>
                 <el-form-item label="邮箱">
                    <el-input v-model="editForm.email"></el-input>
                </el-form-item>
                 <el-form-item label="姓名">
                    <el-input v-model="editForm.name"></el-input>
                </el-form-item>
                <el-form-item label="角色">
                    <el-select v-model="editForm.type" placeholder="请选择角色">
                    <el-option label="用户" value="user"></el-option>
                    <el-option label="管理员" value="admin"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
        </span>
        <!-- 底部区域 -->
        <span slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="editUser">确 定</el-button>
        </span>
        </el-dialog>

          <!-- 更改类型的对话框 -->
        <el-dialog
        title="更改用户类型"
        :visible.sync="updateDialogVisible"
        width="50%">
        <!-- 内容主题区域 -->
        <span>
            <el-form ref="" :model="editTypeForm" label-width="80px">
                <el-form-item label="角色">
                    <el-select v-model="editTypeForm.type" placeholder="请选择角色">
                    <el-option label="管理员" value="admin"></el-option>
                    <el-option label="用户" value="user"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
        </span>
        <!-- 底部区域 -->
        <span slot="footer" class="dialog-footer">
            <el-button @click="updateDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="updateUserType">确 定</el-button>
        </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data(){
        //自定义验证密码
    var validatePass = (rule, value, callback) => {
      let reg = new RegExp("[ ]");
      if (value === "") {
        callback(new Error("请输入密码"));
      } else if (/[\u4E00-\u9FA5]/g.test(value)) {
        callback(new Error("不能输入中文!"));
      } else if (reg.test(value)) {
        callback(new Error("不能输入空格字符"));
      } else{
        callback();
      }
    };
        return{
            //获取用户列表的参数对象
            queryInfo:{
                query:'',
                pagenum:1,  //当前页数
                pagesize:5  //每页显示多少条数据
            },
            type:"",
            total:0,  //总数据数
            userlist: [],//用户数据
            editTypeForm:{},
            addDialogVisible:false, //控制添加对话框的显示与隐藏
            editDialogVisible:false,
            updateDialogVisible:false,
            updateTypeForm:{},
            editForm:{
            }, //查询到的用户信息
            addform: {  //添加的用户信息
                username: '',
                password: '',
                email: '',
                phone: '',
                type:'',
                status:0,//0代表正常
                date:''
            },
             addformRules: {
                username: [
                { required: true, message: "请输入账号", trigger: "blur" },
                { min: 5, message: "请输入正确的账号", trigger: "blur" },
                { max: 11, message: "请输入正确的账号", trigger: "blur" },
                ],
                name:[{ required: true, message: "请输入姓名", trigger: "blur" }],
                email: [
                { required: true, message: "请输入邮箱地址", trigger: "blur" },
                {
                    type: "email",
                    message: "请输入正确的邮箱地址",
                    trigger: ["blur", "change"],
                }
                ],
                password: [
                { required: true, trigger: "blur", validator: validatePass },
                { max: 12, message: "长度不能超过12个字符", trigger: "blur" },
                { min: 6, message: "长度不能少于6个字符", trigger: "blur" },
                ],
                 type: [{ required: true, message: "请选择用户", trigger: "blur" }]
            },

        };
    },
    methods:{
        //修改时间格式
        dataFormat: function(row,column){
            let data = row[column.property]
            if (data ===null) {
                return ''
            }
            let dt = new Date(data)
                return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate()
            },
        async getUserList(){
            //console.log(this.queryInfo);
            //发送请求获取数据
           const {data:res} = await this.$http.get('/users',{params:this.queryInfo});
           console.log(res);
           if(res.status!=200){
                    return this.$message.error(res.msg)
            }else if(res.status==200){
              //  console.log(res);
                this.userlist = res.data.list;
                this.total = res.data.totalCount;
            }
        },
        //监听oagesize改变的事件
        handleSizeChange(newSize){
            //console.log(newSize);
            this.queryInfo.pagesize = newSize;
            //再次调用接口查找用户
            this.getUserList();
        },
        //监听页码值改变的事件
        handleCurrentChange(newPage){
            //console.log(newPage);
            this.queryInfo.pagenum = newPage;
             //再次调用接口查找用户
             this.getUserList();
        },
        //监听switch开关的改变
       async userStateChange(userinfo){
           // console.log(userinfo);
           this.updateTypeForm = userinfo;
           this.updateTypeForm.status = 0;
          //  发送请求改变用户状态
           const {data:res} = await this.$http.post("/edituser",this.updateTypeForm);
           //console.log(res);
            if(res.status!=200){
                return this.$message.error(res.msg);
            }
            if(res.status==200){
                this.$message.success(res.msg)
                this.getUserList();
            }
        },
        //添加用户
         addUser(addform){
              this.$refs[addform].validate(async valid => {
                  if(valid){
                     this.addform.status = 0;
                     this.addform.date = new Date();
                     const{data:res}= await this.$http.post("/users",this.addform)
                   //  console.log(res);
                     if(res.status===200){
                        this.$message.success(res.msg);
                        this.getUserList();
                        this.addDialogVisible = false
                     }else{
                          this.$message.error(res.msg);
                     }
                  }else{
                  //  console.log('error submit!!');
                   this.$message.error("信息校验不合法");
                    return false;
                  }
              }); 
        },
        //修改对话框事件
        async showeditDialogVisible(id){
            const {data:res} = await this.$http.get("/edituser",{params:{"id":id}});
            //console.log(res);
           if(res.status!=200){
                return this.$message.error(res.msg);
            }else if(res.status==200){
                this.editForm = res.data;
                this.$message.success(res.msg)
                
            }
            this.editDialogVisible = true;
            
        },
        async editUser(){
            const {data:res} = await this.$http.post("/edituser",this.editForm);
            if(res.status!=200){
                return this.$message.error(res.msg);
            }
            if(res.status==200){
                this.$message.success(res.msg)
                this.editDialogVisible = false;
                this.getUserList();
            }
        },
        //根据ID删除用户
        async removeUserById(id){
            const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).catch(err=>err);  //相当于err => {return err}
        
          // console.log(confirmResult)
          if(confirmResult != 'confirm'){
              this.$message.info('已取消删除')
          }
           if(confirmResult == 'confirm'){
              const {data:res} = await this.$http.get("/deleteuser",{params:{"id":id}})
              if(res.status!=200){
                return this.$message.error(res.msg);
            }
            if(res.status==200){
                this.$message.success(res.msg)
                this.getUserList();
            }
          }
        },
        //更改用户类型
        updateType(userinfo){
            //console.log(userinfo);
            this.editTypeForm = userinfo;
            this.updateDialogVisible = true;

        },
        //更新用户状态
        async updateUserType(){
              const {data:res} = await this.$http.post("/edituser",this.editTypeForm);
            if(res.status!=200){
                return this.$message.error(res.msg);
            }
            if(res.status==200){
                this.$message.success(res.msg)
                this.getUserList();
                this.updateDialogVisible = false;
            }
        },
         //退出功能
        logout(){
            window.sessionStorage.clear();
            this.$router.push("/login");
        },

    },
    
    created(){
        this.getUserList();
        this.type = window.sessionStorage.getItem("type");
    }
    
}
</script>

<style lang="less" scoped>
.el-pagination{
    margin: 20px 0;
}
.el-dialog__wrapper{
    width: 50%;
    margin: 0 auto;
}
</style>