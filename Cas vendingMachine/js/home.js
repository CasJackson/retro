function selectItem(id) {
    $('#itemIdDisplay').val(id);
}


function loaditems() {

    var itemList = $('#items')
    $.ajax({
        Type: 'GET',
        url: ' http://tsg-vending.herokuapp.com/items',
        success: function (itemArray) {
            $.each(itemArray, function (index, item) {
                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;

                var button = '<button type = "submit" onclick = "selectItem(' + id + ')"><p>' + name + '</p><p>' + price + '</p><p>' + quantity + '</p></button>';




                itemList.append(button);







            });


        },
        error: function (a, b, c) {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later. '))

        }
    });
}

loaditems();



function vendItems() {
    // clearForm();
    var itemList = $('#items')
    $.ajax({
        Type: 'Post',
        url: 'http://tsg-vending.herokuapp.com/money/' + $("#display").val() + '/item/' + $("#itemIdDisplay").val(),
        success: function (data, item) {
            var quarters = data.quarters
            var dimes = data.dimes
            var nickels = data.nickel
            var pennies = data.pennies;



            //TODO: display the change
            $('#items').clear();
            loaditems();
            $("#itemIdDisplay").val("");
            // index = "";
            // totalPaid = 0;
        },
        error: function (a, b, c) {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later. '))



        },
    });
}

function clearForm() {
    $('#display').empty();
}

var totalAmount = 0;
function addMoney(amount) {
    totalAmount = amount + totalAmount
    $('#display').val(totalAmount);
    // totalPaid = totalPaid + 1;
    // $("#totalAmount").val()
}
var selectedId = $(this).attr("id")
function selectItem() {
    var index = selectedId.empty(-1);
    $('#messageDisplay').val($("item-id-" + index).text())
};
vendItems();





var returnChange = 0;
function displayChange() {
    var amountSpent = $("item.price").val()
    returnChange = totalAmount - amountSpent
    $('#changeDisplay').val(returnChange);


}





//     for (var i = 0; i < itemArray.length; i++)
//     itemArray[i].button.onclick = function selectItem(id) {
//  $('#messageDisplay').val($("item-id-" + id).text());

//     }   

// var selectedId = $(this).attr("id")
// index = selectedId.slice(-1);
// $('#messageDisplay').val($("item-id-" + index).text())};


