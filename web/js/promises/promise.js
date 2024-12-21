const cart=["shoes","pants","shirt"]

const promise=createOrder(cart);

promise.then(function(data){
    console.log(data);
    return data;
}).then(function(orderId){
    return proceedToPayment(orderId);
}).then(function (info){
    console.log(info);
})
.catch(function(err){
    console.log(err.data);
})


function createOrder(cart){
    const pr=new Promise(function(resolve,reject){
        // createOrder
        // validateCart
        // orderId
        if(!validateCart(cart)){
            const err=new Error("cart is not valid");
            reject(err);
        }
        // logic for createOrder
        const orderId="123456"
        if(orderId){
            setTimeout(function(){
                resolve(orderId)
            },1000);
        }
    });
    return pr;
}

function validateCart(){
    return true;
}

function proceedToPayment(id){
    return new Promise(function(resolve,reject){
        resolve("success");
    });
}


