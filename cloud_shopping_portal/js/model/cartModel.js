/**
 * Shopping cart data
 */
var cartModel = {

    // Add the items in the shopping cart
    add : function (data, success) {
        czHttp.getJSON('../data/success.json', data, function (responseData) {
            if(responseData.isok){
                success(responseData);
            }
        });
    },

    // Delete the items in the shopping cart
    remove : function (data, success) {
        czHttp.getJSON('../data/success.json', data, function (responseData) {
            if(responseData.isok){
                success(responseData);
            }
        });
    },

    // Modify the quantity of goods
    changeNumber : function (data, success) {
        czHttp.getJSON('../data/success.json', data, function (responseData) {
            if(responseData.isok){
                success(responseData);
            }
        });
    },

    // Modify the quantity of goods
    subtotal : function (success) {
        czHttp.getJSON('../data/orders.json', data, function (responseData) {
            if(responseData.isok){
                success(responseData);
            }
        });
    },

    // Shopping cart list
    list : function (success) {

        czHttp.getJSON('../data/orders.json', {}, function(responseData){
            success(responseData);
        });
    }
};