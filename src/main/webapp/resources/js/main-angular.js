var app = angular.module('cartApp',[]);

app.controller('cartController', ['$scope', '$log', '$http' ,function ($scope, $log, $http){ 

    $scope.refreshCart = function(sessionId){
        $scope.sessionId = sessionId;
        $log.info('Odświeżam koszyk... '+$scope.sessionId);
        $http({ method: 'GET',
                url: '/webshop/rest/cart/'+$scope.sessionId
        }).then( 
        function(response){
            $log.info('Odświeżanie koszyka nr '+$scope.sessionId+' powiodło się. Status= ' + response.status + '.');
            $scope.cart = response.data;
            $scope.calculateTotalQuantity();
        }, function(response){
            $log.error('Podczas odświeżania koszyka nr '+$scope.sessionId+' wystąpił błąd: '+response.data+' , status: '+response.status+'!');
        });
    }

    $scope.removeCart = function(cartId){
        $log.info("Usuwam koszyk nr="+cartId);
        if(cartId===null){
            return;
        }
        $http({ method:'DELETE',
                url:'/webshop/rest/cart/'+cartId
        }).then(
        function(response){
            $log.info('Usunięto koszyk nr '+cartId+'. Status= '+response.status+'.');
            $scope.refreshCart($scope.sessionId);
            $scope.getNumberOfItemsFromCart($scope.sessionId);
        },
        function(response){
            $log.error('Podczas usuwania koszyka nr '+cartId+' wystąpił błąd: '+response.data+' , status: '+response.status+'!');
        });
    }

    $scope.addItemToCart = function(productId){
        $log.info("Dodaje produkt o ID="+productId);

        $http({ method: 'PUT', 
                url:'/webshop/rest/cart/add/'+productId
        }).then(
        function(response){
            $log.info('Dodano product nr '+productId+'. Status= '+response.status+'.');
            $scope.getNumberOfItemsFromCart($scope.sessionId);
//            alert("Produkt dodany do koszyka!");
        },
        function(response){
            $log.error('Podczas dodawania producktu nr '+productId+' wystąpił błąd: '+response.data+' , status: '+response.status+'!');
        });
    }

    $scope.removeItemFromCart = function(productId){
        $log.info("Usuwam z koszyka produkt o ID="+productId);

        $http({ method:'PUT',
                url:'/webshop/rest/cart/delete/'+productId
        }).then(
        function(response){
            $log.info('Usunięto product nr '+productId+'. Status= '+response.status+'.');
            $scope.refreshCart($scope.sessionId);
            $scope.getNumberOfItemsFromCart($scope.sessionId);
        },
        function(response){
            $log.error('Podczas usuwania producktu nr '+productId+' wystąpił błąd: '+response.data+' , status: '+response.status+'!');
        });
    }

    $scope.calculateTotalQuantity = function (){
        var total = 0;
        for(var i = 0; i < $scope.cart.cartItemSet.length ; i++ ){
            total += $scope.cart.cartItemSet[i].quantity;
        }
        $scope.totalQuantity = total;
    }

    $scope.getNumberOfItemsFromCart = function(sessionId){
        $log.info('Sprawdzam ilość przedmiotów w koszyku nr ' + sessionId);
        $http({ method:'GET',
                url:'/webshop/rest/cart/items/'+sessionId
        }).then(
        function(response){
            if(isNaN(response.data) || response.data == 0){
                $scope.numberOfItems = "";
            }else{
                $scope.numberOfItems = response.data;
            }
            setItemCounter($scope.numberOfItems);
            $log.info('Liczba przedmiotów to '+ response.data +'. Status= '+response.status+'.');
        },
        function(response){
            $log.error('Pobranie ilości dla koszyka '+sessionId+' zakończyło się niepowodzeniem! Błąd: '+response.data+' , status: '+response.status+'!');
        });
    }

}]);

var setItemCounter = function(count){
    var cart = document.querySelector("#itemCounterOfCart");
    cart.innerHTML = count;
}