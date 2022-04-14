import Vue from 'vue'
//按需导入组件

import { 
    Button,
    Col,
    Link,
    Row,
    Input,
    Form,
    FormItem,
    Option,
    Select,
    Container,
    Message,
    MessageBox,
    Breadcrumb,
    BreadcrumbItem,
    Card,
    Table,
    TableColumn,
    Pagination,
    Dialog,
    Tooltip
} from 'element-ui'


Vue.use(Button)
Vue.use(Col)
Vue.use(Link)
Vue.use(Input)
Vue.use(Row)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Option)
Vue.use(Select)
Vue.use(Container)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Card)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Pagination)
Vue.use(Dialog)
Vue.use(Tooltip)


Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm;
