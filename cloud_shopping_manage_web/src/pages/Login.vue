<template>
  <v-app>
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>后台管理</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field prepend-icon="person" v-model="username" label="用户名" type="text"/>
                  <v-text-field
                    prepend-icon="lock"
                    v-model="password"
                    label="密码"
                    id="password"
                    :append-icon="e1 ? 'visibility' : 'visibility_off'"
                    :append-icon-cb="() => (e1 = !e1)"
                    :type="e1 ? 'text' : 'password'"
                 ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="doLogin">登录</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
    <v-dialog v-model="dialog" width="300px">
      <v-alert icon="warning" color="error" :value="true">
      用户名和密码不能为空
      </v-alert>
    </v-dialog>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    username: "",
    password: "",
    dialog: false,
    e1:false,
    backPath:'',
  }),
  beforeRouteEnter(to,from,next){
    next(vm => {
      vm._data.backPath = from.path;
    });
  },
  methods: {
    doLogin() {
      if (!this.username || !this.password) {
        this.dialog = true;
        return false;
      }
      const form ={};
      form.username = this.username;
      form.password = this.password;
      console.log("admin登录");
      console.log(this.username);
      console.log(this.password);
      this.$http.post("/auth/adminLogin", this.$qs.stringify(form)).then(resp =>{
        console.log("resp.status:" + resp.status);
        if (resp.status === 204){
          //页面跳转
          console.log("resp.status=204页面跳转");
          if (this.backPath === "/"){
            console.log("页面跳转category");
            this.$router.push("/item/category");
          } else {
            this.$router.push(this.backPath);
          }
        }
      }).catch(() => {
        this.$message.error("账号或者密码错误！");
      });
      // console.log(this.username + " ... " + this.password);
      // this.$router.push("/");
    }
  }
};
</script>
