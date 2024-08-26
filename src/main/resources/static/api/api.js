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
        console.log("name: " +name+", value: "+value)

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
function formatPrice(price){
   return  Intl.NumberFormat().format(price)
}
function reformatPrice(price){
    return parseInt(price.replace(/,/g, ''));
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

function nullDesription(value){
    if (value.trim() == ""){
       return  "Không có mô tả"
    }
    return value;
}

let query_task = null;
function updateTotalPrice() {
    let ids = document.querySelector(".multiChoiceJs").value.split(",").filter(item => item.trim())
    if (ids.length === 0){
        document.querySelector(".totalPrice").value = 0
    }else {
        get(`/admin/api/v1/course/calculateTotalPrice/${ids}`)
            .then(total =>{
                let discount = document.querySelector("input[name='discount']").value
                let price = total- (total * parseInt(discount))/100;
                document.querySelector(".totalPrice").value = price
            })
    }
}

function initializeChoices() {
    document.addEventListener("DOMContentLoaded", function() {
        // Khởi tạo Choices với trạng thái ban đầu
        query_task = new Choices(document.querySelector('#query_task'), {
            removeItemButton: true,
            maxItemCount: 5,
            duplicateItemsAllowed: true,
            choices: [{ value: 0, label: 'Loading...' }]
        });

        fetchCourses().then(courses => {
            query_task.clearChoices();
            query_task.setChoices(courses, 'value', 'label', true);
        });

        query_task.passedElement.element.addEventListener('addItem', function(e) {
            query_task.setChoices([{
                value: e.detail.value,
                label: e.detail.label
            }], 'value', 'label', false);

            document.querySelector(".multiChoiceJs").value += e.detail.value+ ","

            if (document.querySelector(".totalPrice") != undefined){
                updateTotalPrice()
            }
        }, false);

        query_task.passedElement.element.addEventListener('removeItem', function(e) {
            let list = document.querySelector(".multiChoiceJs").value.split(",").filter(item => item.trim())
            let newList = list.filter(item => item !== e.detail.value.toString());
            document.querySelector(".multiChoiceJs").value =newList+","

            if (document.querySelector(".totalPrice") != undefined){
                updateTotalPrice()
            }
            reset();
        }, false);

        function reset() {
            fetchCourses().then(courses => {
                query_task.clearChoices();
                query_task.setChoices(courses, 'value', 'label', false);

            });
        }

    });
}
