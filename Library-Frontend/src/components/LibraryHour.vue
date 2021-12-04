<template>
  <div id="libraryhour">
    <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
    <div class="card-body mt-3 mb-3">
      <div class="card-body mt-3 mb-3">
      	<h1>Search For Colleague?</h1>
        <input class="text-field" type="text" v-model="librarianId" placeholder="Search by userId">
        <i class="bi-search search-icon" v-bind:diabled="!librarianId" @click="searchLibraryHour(librarianId)"></i>
        <input class="text-field" type="text" v-model="username" placeholder="Search by username">
        <i class="bi-search search-icon" v-bind:diabled="!username" @click="searchLibraryHourByUsername(username)"></i>
      </div>
    </div>
    <h1>Weekly Schedule</h1>
    <table>
      <tr>
        <td>Day</td>
        <td v-for="libraryHour in libraryHours">
        {{libraryHour.day}}
        </td>
      </tr>
      <tr>
        <td>Start Time</td>
        <td v-for="libraryHour in libraryHours">
        {{libraryHour.startTime}}
        </td>
      </tr>
      <tr>
        <td>End Time</td>
        <td v-for="libraryHour in libraryHours">
        {{libraryHour.endTime}}
        </td>
      </tr>
    </table>
    <button @click="gotoLibrarian()" class="btn">Back</button>
    <p>
      <span v-if="error" style="color:red">{{error}}</span>
    </p>
    
  </div>
</template>



<script src="./LibraryHour.js"></script>



<style>
  :root {
    --primary-color: #ED1B2F;
    --light-color: #EEF1EF;
    --dark-color: #262626;
    --dark-secondary: #414141;
    --font: "Roboto"
  }
  #libraryhour {
    font-family: var(--font);
    color: var(--light);
    background: var(--dark-color);
    height: 100vh;
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
  .navbar-main {
    font-family: var(--font);
    font-size: 24px;
    background-color: var(--primary-color);
    height: 65px;
  }
  table {
  margin-left: auto; 
  margin-right: auto;
  border: 5px solid white;
  table-layout: fixed ;
  width: 90% ;
  }
  td {
  border: 5px solid white;
  width: 25% ;
  }
  th {
  border: 5px solid white;
  }
</style>