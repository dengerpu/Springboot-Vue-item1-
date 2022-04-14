<template>
  <el-container style="height: 100%; background: antiquewhite">
    <el-container style="padding: 10px; line-height: 0; background: #66b5cd; margin: 0 auto">
      <el-row
        style="height: 100%; margin: 0 auto"
        type="flex"
        class="row-bg loginpageback"
        justify="space-around"
      >
        <el-row>
          <!-- 标题 -->
          <el-col :xs="0" :sm="12" :md="13" :lg="15" :xl="15">
            <div class="grid-content" style="margin-top: -10px">
              <h2
                style="font-size: 40px; z-index：2; position:absolute; left:0px; font-weight:700; color:#fff;"
              >
              </h2>
              <img
                src="../assets/img/实名认证.svg"
                style="background: #66b5cd"
                width="800px"
                alt=""
                class=""
              />
            </div>
          </el-col>
          <!-- 找回密码表单 -->
          <el-col :xs="24" :sm="9" :md="9" :lg="9" :xl="8">
            <div class="grid-content">
              <el-form
                :model="ruleForm"
                :rules="rules"
                ref="ruleForm"
                label-width="100px"
                class="myform"
              >
                <el-row>
                  <el-col :span="24" style="text-align: center;">
                    <div class="grid-content loginlogo">
                      <img
                        src="../assets/img/找回密码.svg"
                        width="50%"
                        height="30%"
                        alt=""
                      />
                    </div>
                  </el-col>
                </el-row> 
                <el-form-item label="账号" prop="username">
                  <el-input
                    prefix-icon="el-icon-user-solid"
                    placeholder="请输入账号"
                    v-model.trim="ruleForm.username"
                  ></el-input>

                </el-form-item>
                <el-form-item label="旧密码 " prop="pwd">
                  <el-input
                    placeholder="请输入邮箱"
                    prefix-icon="el-icon-message"
                    v-model.trim="ruleForm.pwd"
                  ></el-input>
                </el-form-item>

                <el-form-item label="输入新密码" prop="password" auto-complete="off">
                  <el-input
                    prefix-icon="el-icon-lock"
                    placeholder="请输入6位密码"
                    type="password"
                    v-model.trim="ruleForm.password"
                    show-password
                  ></el-input>
                </el-form-item>
                <el-form-item
                  label="确认密码"
                  prop="checkPass"
                  auto-complete="off"
                >
                  <el-input
                    placeholder="请再次输入密码"
                    prefix-icon="el-icon-lock"
                    type="password"
                    v-model.trim="ruleForm.checkPass"
                    show-password
                  ></el-input>
                </el-form-item>

                <el-form-item>
                  <div style="text-align: left">
                    <el-button
                      type="primary"
                      round
                      @click="submitForm()"
                      >确认修改</el-button
                    >
                    
                    <el-button round @click="resetForm('ruleForm')"
                      >重置</el-button>
                    <el-button type="info" round plain @click="back('ruleForm')"
                      >返回</el-button>
                  </div>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
 
        </el-row>
      </el-row>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      let reg = new RegExp("[ ]");
      if (value === "") {
        callback(new Error("请输入密码"));
      } else if (/[\u4E00-\u9FA5]/g.test(value)) {
        callback(new Error("不能输入中文!"));
      } else if (reg.test(value)) {
        callback(new Error("不能输入空格字符"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
  

    return {
       content: '获取验证码', // 获取验证码按钮文字
       flag: false,//按钮是否可取
       totalTime: 30,//倒计时时间
       color:"success",//按钮颜色类型
       checkLoading:false,//缓存
       
       codeTime:30,

      ruleForm: {
        username: "",
        password: "",
        email: "",
        checkPass: "",
        verifyCode:"",
        choice:[],
      },
       
      successResponse: [],

      rules: {
        verifyCode: [
          { required: true, message: "请输入验证码", trigger: "blur" },
          ],
        username: [
          { required: true, message: "请输入账号", trigger: "blur" },
          { min: 10, message: "请输入正确的学号/职工号", trigger: "blur" },
          { max: 10, message: "请输入正确的学号/职工号", trigger: "blur" },
          {
            validator: function (rule, value, callback) {
              //校验空格
              let reg = new RegExp("[ ]");
              if (reg.test(value)) {
                callback(new Error("不能输入空格字符"));
              } else {
                //校验通过
                callback();
              }
            },
            
          },
        ],
        choice: [{ required: true, message: "请选择用户", trigger: "blur" }],

        email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePass },
          { max: 12, message: "长度不能超过12个字符", trigger: "blur" },
          { min: 6, message: "长度不能少于6个字符", trigger: "blur" },
        ],
        checkPass: [
          { required: true, trigger: "blur", validator: validatePass2 },
        ],
      },
    };
  },
  methods: {

    submitForm(){
      alert("请联系管理员哈哈哈哈哈！");
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    back(formName){
      this.$refs[formName].resetFields();
      sessionStorage.clear();
      this.$router.push({ name: "login" });
    }

  },
};
</script>

<style>
.register {
  background: url("../assets/img/实名认证.svg");
  background-size: 125% 130%;
  /* border: 1px; */
  width: 100%;
}

.myform {
  padding: 20px;
  z-index: 2;
  width: 400px;
  background: #fff;
  -webkit-border-radius: 4px;
  border-radius: 4px;
  background-image: -webkit-gradient(
    linear,
    left top,
    left bottom,
    from(#fff),
    to(#f4f4f4)
  );
  background-image: -webkit-linear-gradient(top, #fff, #f4f4f4);
  background-image: -moz-linear-gradient(top, #fff 0, #f4f4f4 100%);
  background-image: linear-gradient(-180deg, #fff, #f4f4f4);
  -webkit-box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.1);
}
</style>
