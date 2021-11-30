<template>
  <div id="librarian">
     <nav class="navbar navbar-expand-lg fixed-top navbar-main" id="mainNav">
      <div class="container-fluid">
        <a class="navbar-header">Library</a>
      </div>
    </nav>
   <div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-md-3 mt-5">
        <br>
          <h1>Get Offline User</h1>
          <input class="text-field" type="text" v-model="id_query" placeholder="Offline User ID">
          <i class="bi-search search-icon" v-bind:diabled="!id_query" @click="searchOfflineUser(id_query)"></i>
          <li v-for="id in id_response" :key="id.firstName">
                {{item.firstName}}
          </li>

          <br>
        </div>
      </div>            
   </div>
    <br>
    <br>
    <br>
    <br>
    <br>
   <div class="container">
   <div class="row">
    <div class="col-sm">
    <div class="card-body mt-3 mb-3">
    <h1>Create Album</h1>
    <input class="text-field" type="text" v-model="title" placeholder="Title">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isArchive">
    <br>
    <input class="text-field" type="text" v-model="releaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="numSongs" placeholder="Number of Songs">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="available">
    <br>
    <input class="text-field" type="text" v-model="genre" placeholder="Genre">
    <input class="text-field" type="text" v-model="creatorId" placeholder="Creator ID">
    <button v-bind:diabled="!title||!releaseDate||!numSongs||!genre||!creatorId" @click="createAlbum(title, isReservable, isArchive, releaseDate, numSongs, available, genre, creatorId)" class="btn">Create Album</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Update Album</h1>
    <input class="text-field" type="text" v-model="id" placeholder="Item ID">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isArchive">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="available">
    <br>
    <button v-bind:diabled="!id" @click="updateAlbum(id, isReservable, isArchive, available)" class="btn">Update Album</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Remove Album</h1>
    <input class="text-field" type="text" v-model="id" placeholder="Item ID">
    <button v-bind:diabled="!id" @click="deleteAlbum(id)" class="btn">Remove Album</button>
    <br>
    </div>
    </div>
    <div class="col-sm">
    <div class="card-body mt-3 mb-3">
    <h1>Create Book</h1>
    <input class="text-field" type="text" v-model="title" placeholder="Title">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isArchive">
    <br>
    <input class="text-field" type="text" v-model="releaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="numPages" placeholder="Number of Pages">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="available">
    <br>
    <input class="text-field" type="text" v-model="genre" placeholder="Genre">
    <input class="text-field" type="text" v-model="creatorId" placeholder="Creator ID">
    <button v-bind:diabled="!title||!releaseDate||!numPages||!genre||!creatorId" @click="createBook(title, isReservable, isArchive, releaseDate, numPages, available, genre, creatorId)" class="btn">Create Book</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Update Book</h1>
    <input class="text-field" type="text" v-model="id" placeholder="Item ID">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isArchive">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="available">
    <br>
    <button v-bind:diabled="!id" @click="updateBook(id, isReservable, isArchive, available)" class="btn">Update Album</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Remove Book</h1>
    <input class="text-field" type="text" v-model="id" placeholder="Item ID">
    <button v-bind:diabled="!id" @click="deleteBook(id)" class="btn">Remove Book</button>
    <br>
    </div>
    </div>
    <div class="col-sm">
    <div class="card-body mt-3 mb-3">
    <h1>Create Movie</h1>
    <input class="text-field" type="text" v-model="title" placeholder="Title">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isArchive">
    <br>
    <input class="text-field" type="text" v-model="releaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="duration" placeholder="Duration">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isAvailable">
    <br>
    <input class="text-field" type="text" v-model="genre" placeholder="Genre">
    <input class="text-field" type="text" v-model="creatorId" placeholder="Creator ID">
    <button v-bind:diabled="!title||!releaseDate||!duration||!genre||!creatorId" @click="createMovie(title, isReservable, isArchive, isAvailable, releaseDate, duration, genre, creatorId)" class="btn">Create Movie</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Update Movie</h1>
    <input class="text-field" type="text" v-model="id" placeholder="Item ID">
    <br>
    <label>Is Reservable:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isReservable">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isArchive">
    <br>
    <label>Is Available:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isAvailable">
    <br>
    <button v-bind:diabled="!id" @click="updateMovie(id, isReservable, isArchive, isAvailable)" class="btn">Update Movie</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Remove Movie</h1>
    <input class="text-field" type="text" v-model="id" placeholder="Item ID">
    <button v-bind:diabled="!id" @click="deleteMovie(id)" class="btn">Remove Movie</button>
    <br>
    </div>
    </div>
    <div class="col-sm">
    <div class="card-body mt-3 mb-3">
    <h1>Create Newspaper</h1>
    <input class="text-field" type="text" v-model="title" placeholder="Title">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isArchive">
    <br>
    <input class="text-field" type="text" v-model="releaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="creatorId" placeholder="Creator ID">
    <button v-bind:diabled="!title||!releaseDate||!creatorId" @click="createNewspaper(title, isArchive, releaseDate, creatorId)" class="btn">Create Newspaper</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Update Newspaper</h1>
    <input class="text-field" type="text" v-model="id" placeholder="Item ID">
    <input class="text-field" type="text" v-model="title" placeholder="Title">
    <br>
    <label>Is Archive:</label>
    <br>
    <input type="checkbox" class="text-field" v-model="isArchive">
    <br>
    <input class="text-field" type="text" v-model="releaseDate" placeholder="Release Date">
    <input class="text-field" type="text" v-model="creatorId" placeholder="Creator ID">
    <button v-bind:diabled="!id||!title||!releaseDate||!creatorId" @click="updateNewspaper(id, title, isArchive, releaseDate, creatorId)" class="btn">Update Newspaper</button>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Remove Newspaper</h1>
    <input class="text-field" type="text" v-model="id" placeholder="Item ID">
    <button v-bind:diabled="!id" @click="deleteNewspaper(id)" class="btn">Remove Newspaper</button>
    <br>
    </div>
    </div>
    </div>
    </div>
<div class="container">
      <div class="row d-flex justify-content-center">
        <div class="col-md-3 mt-5">
        <br>
            <button class="btn mt-3 mb-5" @click="gotoOfflineUserView()">Offline User Services</button>
          <br>
        </div>
        <div class="row d-flex justify-content-center">
          <div class="col-xl-12 mt-3 mb-3">
            <button @click="logout()" class="btn">Logout</button>
          </div>
      </div>
      </div>            
   </div>
    <p>
      <span v-if="error" style="color:red">{{ error }}</span>
    </p>
  </div>    
</template>

<script src="./librarian.js"></script>
<style>
  :root {
    --primary-color: #ED1B2F;
    --light-color: #EEF1EF;
    --dark-color: #262626;
    --dark-secondary: #414141;
    --font: "Roboto"
  }
  #librarian {
    font-family: var(--font);
    color: var(--light);
    background: var(--dark-color);
    height: 200vh;
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