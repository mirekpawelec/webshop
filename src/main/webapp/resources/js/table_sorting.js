(function(){
    try{
        var tab = document.querySelector("#sortTable"),
            tabHead = tab.querySelectorAll("table thead tr th"),
            tabBody = tab.querySelectorAll("table tbody tr");
    } catch(e){
            console.log("No table found!");
            return;
    }

    for(var i = 0; i < tabHead.length ; i++){
            tabHead[i].onclick = sortBy;
    }

    function convertToTable(pseudoTable){
            var table = [];
            for(var i = 0 ; i < pseudoTable.length ; i++){
                    table.push(pseudoTable[i]);
            }
            return table;
    }

    function clearClass(headList){
            var span;
            for(var i = 0 ; i < headList.length ; i++){
                span = headList[i].querySelector("span");
                if(span){
                    headList[i].removeChild(headList[i].querySelector("span"));
                }
            }
    }

    function sortBy(e){
        var targetSpan = e.target.querySelector("span"),
            tabHeadArr = convertToTable(tabHead),
            tabBodyArr = convertToTable(tabBody),
            index = tabHeadArr.indexOf(e.target),
            df = document.createDocumentFragment(),
            order,
            newSpan;

        if(!targetSpan){
            order = "glyphicon glyphicon-sort-by-attributes";	
        } else {
            order = targetSpan.className === "glyphicon glyphicon-sort-by-attributes" ? "glyphicon glyphicon-sort-by-attributes-alt" : "glyphicon glyphicon-sort-by-attributes";
        }

        clearClass(tabHead);	

        tabBodyArr.sort(function(a, b){
            var rowA, rowB;
            try{
                    rowA = a.children[index].textContent;
                    rowB = b.children[index].textContent;
            } catch(err){}
            if(rowA > rowB){
               return order === "glyphicon glyphicon-sort-by-attributes"? 1 : -1;
            }else if(rowA < rowB){
               return order === "glyphicon glyphicon-sort-by-attributes"? -1 : 1;
            }else{
               return 0;	
            }
        });

        tabBodyArr.forEach(function(row){
            df.appendChild(row);
        });

        newSpan = document.createElement("span");
        newSpan.setAttribute("class", order);
        e.target.appendChild(newSpan);

        tab.querySelector("tbody").appendChild(df);
    }

})()