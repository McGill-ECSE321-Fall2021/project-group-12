<template>
  <div id="login">
    <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
    <div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-md-12">
          <p class="mt-4">Are you sure you want to cancel this reservation?</p>
          <button @click="cancelReservationConfirm()" class="btn">Yes</button>
          <button @click="cancelReservationReject()" class="btn">Cancel</button>
        </div>
      </div>
    </div>
    <p class="mt-3">
      <span v-if="error" style="color: red">{{ error }}</span>
    </p>
  </div>
</template>
<script src="./cancelReservation.js"></script>
<style>
:root {
  --primary-color: #ed1b2f;
  --light-color: #eef1ef;
  --dark-color: #262626;
  --dark-secondary: #414141;
  --font: 'Roboto';
}
#login {
  font-family: var(--font);
  color: var(--light);
  background: var(--dark-color);
  height: 100vh;
}
.navbar-main {
  font-family: var(--font);
  font-size: 24px;
  background-color: var(--primary-color);
  height: 65px;
}

.btn {
  color: var(--dark-color);
  background-color: var(--light-color);
  border-color: var(--dark-secondary);
  text-decoration: none;
  transition: 0.3s;
  margin: 5px;
  padding-left: 25px !important;
  padding-right: 25px !important;
  border-radius: 3em !important;
}

.btn:hover,
.btn:focus,
.btn:active {
  color: var(--light-color);
  background-color: var(--primary-color);
  transition: 0.3s;
}

.regular-text {
  font-family: var(--font);
}
</style>
