function formToObject(formSelector){
    const form = document.querySelector(formSelector)
    const obj = {};

    const inputs = form.querySelectorAll('input')
    for (const input of inputs) {
        if ( input.getAttribute('name')!= null) {
            const name = input.getAttribute('name');
            const value = input.value;
            obj[name] = value
        }
    }
    const selects = form.querySelectorAll('select')
    for (const select of selects){
        const name = select.getAttribute('name');
        const value = select.value;

        obj[name] = value
    }

    const textareas = form.querySelectorAll('textarea')
    for (const textarea of textareas){
        const name = textarea.getAttribute('name');
        const value = textarea.value;

        obj[name] = value
    }

    return obj;
}

let accountInfor = {}
function getAccount(accountInfor) {
    return  get("/api/v1/account/getAccount")
        .then(accountData =>{
            accountInfor.code = accountData.code;
            accountInfor.id = accountData.id;
            accountInfor.username = accountData.username;
            accountInfor.url = accountData.url;
        })
}

function get(path, params = {}){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type","application/json");
    const paramUrl = new URLSearchParams(params)
    return fetch(`${path}?${paramUrl}`,{
        method: 'GET',
        header: myHeaders,
    }).then(response => response.json())
}

function post(path,body){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type","application/json");
    return fetch(path,{
        method: 'POST',
        body: JSON.stringify(body),
        headers: myHeaders,
    }).then(response => response.json())
        .catch(error => {
            console.error('Error:', error);
        });
}

function postImages(path,body){
    var myHeaders = new Headers();
    return fetch(path,{
        method: 'POST',
        body: body,
        headers: myHeaders,
    }).then(response => response.json())
        .catch(error => {
            console.error('Error:', error);
        });
}

function putImages(path,body){
    var myHeaders = new Headers();
    return fetch(path,{
        method: 'PUT',
        body: body,
        headers: myHeaders,
    }).then(response => response.json())
        .catch(error => {
            console.error('Error:', error);
        });
}

function put(path,body){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type","application/json");
    return fetch(path,{
        method: 'PUT',
        body: JSON.stringify(body),
        headers: myHeaders,
    }).then(response => response.json())
}

function deleteMapping(path){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type","application/json");
    return fetch(path,{
        method: 'DELETE',
        headers: myHeaders,
    })
}
const _$=$
function showPagination({
                    totalItems,
                    limit,
                    currentPage,
                    onPageClick,
                    }){
    $('#pagi').pagination({
            items: totalItems,
            itemsOnPage: limit,
            currentPage: currentPage,
            onPageClick: onPageClick,
            prevText: "&laquo;",
            nextText: "&raquo;"
    })
}