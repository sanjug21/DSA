function x(){
    var a=7;

    function y(){
        console.log(a);
    }
    return y
}

var z=x()

z()


// function alongs with its lexical environment bundles together closures