function generate_card (id, name, email){
    return `
      <div id="card" class="card py-2 px-4 d-flex flex-column justify-content-between align-items-center col-sm-4 col-lg-3 card-character-home">
        <div class="card-body text-center">
          <h4 class="title card-title">${name}</h4>
        </div>
        <div class="card-body text-center">
          <h3 class="title card-title">${email}</h3>
        </div>
   
      </div>`;
  }
  
  export default generate_card;