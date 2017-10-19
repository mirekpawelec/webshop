
    
    function resetFilters(){
        document.getElementById("btnReset").onclick = function(){
            document.getElementById("manufacturer").value = "---------------";
            document.getElementById("category").value = "---------------";
            document.getElementById("minUnitPrice").value = "";
            document.getElementById("maxUnitPrice").value = "";
        }
    }
    
    var btnAddress = document.querySelector("#btnChangeAddress");
//    btnAddress.onclick = function(e){
//        console.log("Odświeżam...");
//        window.location.reload();
//    };


