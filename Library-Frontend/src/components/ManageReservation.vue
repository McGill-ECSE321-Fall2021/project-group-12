<template>
  <div id="manageReservation">
    <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
    <div class="card-body mt-3 mb-3">
      <h1>Reservation Management</h1>
    </div>
    
    <div class="card-body mt-3 mb-3">
      <h3>Delete Reservation</h3>
      <input class="text-field" type="text" v-model="reservationId" placeholder="Enter Reservation ID">
      <button @click="deleteReservation(reservationId)" class="btn">Delete</button>
    </div>
    
    
    <div class="container">
      <div class="row">
        <div class="col-md-3 mt-3 mr-3 left-col">
          
          <h3>Search Reservation By User Id</h3>
          <input class="text-field" type="text" v-model="userId" placeholder="Search by userId">
          <i class="bi-search search-icon" v-bind:diabled="!userId" @click="searchReservationByUserId(userId)"></i>
          
          <li class="item-list left-align" v-for="reservation in userReservations" :key="reservation.reservationId">
          <b><i>ID:</i></b> &ensp;{{reservation.reservationId}}<br>
          <ul>
          	<li><b><i>User Id:</i></b> &ensp;{{reservation.user.userId}}</li>
          	<li><b><i>User Full Name:</i></b> &ensp;{{reservation.user.lastName}},&ensp;{{reservation.user.firstName}}</li>
            <li><b><i>Start Date:</i></b> &ensp;{{reservation.timeSlot.startDate}}</li>
            <li><b><i>End Date:</i></b> &ensp;{{reservation.timeSlot.endDate}}</li>
            <li><b><i>Start Time:</i></b> &ensp;{{reservation.timeSlot.startTime}}</li>
            <li><b><i>End Time:</i></b> &ensp;{{reservation.timeSlot.endTime}}</li>
          </ul>
          
          <table>
		      <tr>
		        <td>Item Id:</td>
		        <td>Title:</td>
		        
		      </tr>
		      <tr v-for="item in reservation.items">
		        <td>
		          {{item.itemId}}
		        </td>
		        <td>
		          {{item.title}}
		        </td>
		      </tr>
		      
		   </table>
          <hr>
          </li>
          
          <button @click="getReservations()" class="btn">Browse Reservations</button>
          <li class="item-list left-align" v-for="reservation in reservations" :key="reservation.reservationId">
          <b><i>ID:</i></b> &ensp;{{reservation.reservationId}}<br>
          <ul>
          	<li><b><i>User Id:</i></b> &ensp;{{reservation.user.userId}}</li>
          	<li><b><i>User Full Name:</i></b> &ensp;{{reservation.user.lastName}},&ensp;{{reservation.user.firstName}}</li>
            <li><b><i>Start Date:</i></b> &ensp;{{reservation.timeSlot.startDate}}</li>
            <li><b><i>End Date:</i></b> &ensp;{{reservation.timeSlot.endDate}}</li>
            <li><b><i>Start Time:</i></b> &ensp;{{reservation.timeSlot.startTime}}</li>
            <li><b><i>End Time:</i></b> &ensp;{{reservation.timeSlot.endTime}}</li>
          </ul>
          
          <table>
		      <tr>
		        <td>Item Id:</td>
		        <td>Title:</td>
		        
		      </tr>
		      <tr v-for="item in reservation.items">
		        <td>
		          {{item.itemId}}
		        </td>
		        <td>
		          {{item.title}}
		        </td>
		      </tr>
		      
		   </table>
          <hr>
          </li>
        </div>
     </div>
    </div>
    
    
    <button @click="gotoLibrarian()" class="btn">Back</button>
    <p>
      <span v-if="error" style="color:red">{{error}}</span>
    </p>
  </div>
</template>



<script src="./manageReservation.js"></script>



<style>
  :root {
    --primary-color: #ED1B2F;
    --light-color: #EEF1EF;
    --dark-color: #262626;
    --dark-secondary: #414141;
    --font: "Roboto"
  }
  #manageReservation {
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