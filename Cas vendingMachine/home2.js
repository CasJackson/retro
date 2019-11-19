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
var ajaxResults = null;
function vendItem() {
   var returnChange = $('#money')
    $.ajax({
        type: "POST",
        url: 'http://tsg-vending.herokuapp.com/money/' + $("#display").val() + '/item/' + $("#itemIdDisplay").val(),
        data: JSON.stringify({

        }),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        success: function (data) {
            
            

            ajaxResults = data.quarters + " qaurters " + data.dimes + " dimes" + data.nickels + " nickels " + data.pennies + " pennies " ;
            $('#items').empty();
            $('#messageDisplay').val(ajaxResults);
            loaditems();
           
            // remove old buttons
            //reload button call load items again
            // 
        },
        error: function (jqXHR, textStatus, errorThrown) {
          

            $('#messageDisplay').val(jqXHR.responseJSON.message);

        }
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
// var selectedId = $(this).attr("id")
// function selectItem() {

//     $('#itemIdDisplay').val($("item-id-" + selectedId).text())
// };


function endVend(){

    $('#display').val("");
    $('#messageDisplay').val("");
    $('#itemIdDisplay').val("");
    totalAmount =0;
}










