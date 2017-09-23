function resetFilters(){
    document.getElementById("btnReset").onclick = function(){
        document.getElementById("manufacturer").value = "---------------";
        document.getElementById("category").value = "---------------";
        document.getElementById("minUnitPrice").value = "";
        document.getElementById("maxUnitPrice").value = "";
    }
}
window.onload = function(){
    resetFilters();
}