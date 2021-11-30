<template>
  <div id="offlineuser">
     <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
    <br>

<div class="container">
   <div class="row">
    <div class="col-sm">
      <br>
          <h1>Create Offline Account</h1>
           <div class="card-body mt-3 mb-3">
            <input class="text-field" type="text" v-model="createFirstName" placeholder="First Name">
            <input class="text-field" type="text" v-model="createLastName" placeholder="Last Name">
            <input class="text-field" type="text" v-model="createAddress" placeholder="Address">
            <br>
            <label>Local:</label>
            <br>
            <input type="checkbox" class="text-field" v-model="createIsLocal">
            <br>
            <button v-bind:diabled="!createFirstName||!createLastName||!createAddress" @click="signUpOffline(createFirstName, createLastName, createAddress, createIsLocal)" class="btn">Register</button>
          </div>
    </div>
    <div class="col-sm">
      <br>
          <h1>Update Offline Account</h1>
           <div class="card-body mt-3 mb-3">
            <input class="text-field" type="text" v-model="updateId" placeholder="Id">
            <input class="text-field" type="text" v-model="updateFirstName" placeholder="First Name">
            <input class="text-field" type="text" v-model="updateLastName" placeholder="Last Name">
            <input class="text-field" type="text" v-model="updateAddress" placeholder="Address">
            <br>
            <label>Local:</label>
            <br>
            <input type="checkbox" class="text-field" v-model="updateIsLocal">
            <button v-bind:diabled="!updateId||!updateFirstName||!updateLastName||!updateAddress" @click="updateOffline(updateId, updateFirstName, updateLastName, updateAddress, updateIsLocal)" class="btn">Update</button>
          </div>
    </div>
    <div class="col-sm">
      <br>
          <h1>Delete Offline Account</h1>
           <div class="card-body mt-3 mb-3">
            <input class="text-field" type="text" v-model="deleteId" placeholder="Id">
            <button v-bind:diabled="!deleteId" @click="deleteOffline(deleteId)" class="btn">Delete</button>
            <br>
          </div>
    </div>
   </div>
   </div>
   <br>

  <div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-md-3 mt-5">
        <h1>Offline User Functions</h1>
            <button lass="btn mt-3 mb-5" @click="gotoOfflineFunctionView()" class="btn">Offline Functions</button>

        </div>
      </div>            
   </div>

   <div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-md-3 mt-5">
        <br>
          <br>
          <button class="btn mt-3 mb-5" @click="gotoLibrarianView()">Back</button>
        </div>
      </div>            
   </div>




    <p>
      <span v-if="error" style="color:red">{{ error }}</span>
    </p>
  </div>    
</template>

<script src="./offlineUser.js"></script>
<style>
  :root {
    --primary-color: #ED1B2F;
    --light-color: #EEF1EF;
    --dark-color: #262626;
    --dark-secondary: #414141;
    --font: "Roboto"
  }
  #offlineuser {
    font-family: var(--font);
    color: var(--light);
    background: var(--dark-color);
    height: 120vh;
  }
  .navbar-main {
    font-family: var(--font);
    font-size: 24px;
    background-color: var(--primary-color);
    height: 65px;
  }
  .text-field {
    text-align: center;
    padding: 6px;
    padding-top: 8px;
    padding-bottom: 8px;
    width: 200px;
    font-size: 15px;
    transition: 0.3s;
    background-color: var(--light-color);
    border-color: var(--dark-color);
    border: none;
    border-radius: 3em;
    margin: 5px;
  }
  .card {
    background-color: var(--dark-secondary);
  }
  .btn {
    color: var(--dark-color);
    background-color: var(--light-color);
    border-color: var(--dark-secondary);
    text-decoration: none;
    transition: 0.3s;
    margin:5px;
    padding-left: 25px !important;
    padding-right: 25px !important;
    border-radius: 3em !important;
  }

  .btn:hover, .btn:focus, .btn:active {
    color: var(--light-color);
    background-color: var(--primary-color);
    transition: 0.3s;
  }

  .regular-text {
    font-family: var(--font);

  }

  .search-icon {
    padding: 5px;
    font-size: 16px;
    width: 10px;
    text-align: center;
    text-decoration: none;
    border-radius: 100%;
    color: var(--primary-color);
    transition: 0.3s;
}

 .search-icon:hover, .search-icon:active, .search-icon:focus{
    padding: 5px;
    font-size: 16px;
    width: 10px;
    text-align: center;
    text-decoration: none;
    border-radius: 100%;
    color: var(--light-color);
    transition: 0.3s;
    cursor:pointer;
}
</style>