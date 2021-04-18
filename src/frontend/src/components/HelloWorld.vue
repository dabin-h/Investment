<!--  <template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>
    <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p>
  </div>
</template>-->

<template>
  <div class="container">
    <h1>{{ msg }}</h1>

    <form @submit.prevent="submitForm">
      <div>
        <label for="email">email</label>
        <input type="text" id="email" v-model="email" />
      </div>
      <div>
        <label for="name">성함</label>
        <input type="text" id="name" v-model="name" />
      </div>
      <div>
        <label for="password">비밀번호</label>
        <input type="password" id="password" v-model="password" />
      </div>
      <div>
        <label for="passwordConfirm">비밀번호 확인</label>
        <input type="password" id="passwordConfirm" v-model="passwordConfirm" />
      </div>
      <div>
        <label for="position">직책</label>
        <input type="text" id="position" v-model="position" />
      </div>
      <div>
        <label for="teamName">팀 이름</label>
        <input type="text" id="teamName" v-model="teamName" />
      </div>
      <button type="submit">회원가입</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'SignupForm',
  data() {
    return {
      email: '',
      name: '',
      password: '',
      passwordConfirm: '',
      position: '',
      teamName: '',
    };
  },
  methods: {
    submitForm() {
      console.log('dd');
    },
  },
};
</script>

<style scoped></style>

<script>
export default {
  name: 'HelloWorld test',
  props: {
    msg: String
  }
}
//
// async submitForm() {
//
//   //validation 생략
//
//   const userData = {
//     email: this.email,
//     name: this.name,
//     password: this.password,
//     position: this.position,
//     teamName: this.teamName,
//   };
//
//   const response = await registerUser(userData);
//   if (response.status == 200) {
//     alert('환영합니다.');
//     this.$router.push('/login');
//   } else {
//     alert(response.data);
//   }
// }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
