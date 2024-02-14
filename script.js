const url = "http://localhost:8080/lembretes/usuario/1";

function hideLoader(){
document.getElementById("loading").style.display = "none";
}

function show(lembrete){
    let tab = `
        <thead>
            <th scope = "col">#</th>
            <th scope = "col">Descrição da Tarefa</th>
            <th scope = "col">Usuário</th>
            <th scope = "col">ID</th>
        </thead>`;

        for (let lembretes of lembrete){
            tab += `
                <tr>
                    <td scope="row">${lembretes.id}</td>
                    <td scope="row">${lembretes.descricao}</td>
                    <td scope="row">${lembretes.usuario.username}</td>
                    <td scope="row">${lembretes.usuario.id}</td>
                </tr>
            `
        }

        document.getElementById("lemb").innerHTML = tab;
}
async function getAPI(url){
    const response = await fetch(url, {method: "GET"});

    var data = await response.json();
    console.log(data);
    if(response)
        hideLoader();
    show(data);
}
getAPI(url);