
import generate_card from "./generateUserCard.js";
import { getAllUsers } from "./serviceUsers.js";



const allusers = await getAllUsers();
const listusers = allusers;


console.log(allusers);
console.log("lista:",listusers)

const basicInfoCharacters = [];

const sectionCard = document.getElementById("section-cards");


for (const user of listusers) {
    const usuario_id = user.id;
    const usuario_name = user.name;
    const usuario_email = user.email;

    const newCard = generate_card(usuario_id, usuario_name, usuario_email);

    sectionCard.innerHTML += newCard;


}

