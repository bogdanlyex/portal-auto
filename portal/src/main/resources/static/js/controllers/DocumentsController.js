angular.module('portal_app')
    .controller("DocumentsController", ["$scope", "$http", function ($scope, $http) {
        $scope.documents = [];
        $scope.document = {};
        $scope.opened=false;

        $http.get('/documents').success(function (response) {
            $scope.documents = response;
        });

        $scope.modalShown = false;
        $scope.toggleModal = function () {
            $scope.modalShown = !$scope.modalShown;
            $scope.document = {};
            $scope.submitDocument = function () {
                console.log($scope.document);

                $http.post('/documents/create', $scope.document).
                    success(function (data, status, headers, config) {
                        console.log(data);
                        $scope.documents.push(data);
                        $scope.modalShown = !$scope.modalShown;

                    }).
                    error(function (data, status, headers, config) {
                        console.log(data);
                    });
            }
        };

        $scope.editToggleModalShown = false;
        $scope.editToggleModal = function (documentId) {
            $http.get('/documents/' + documentId).success(function (response) {
                $scope.document = response;
                $scope.editToggleModalShown = !$scope.editToggleModalShown;
            });
        };

        $scope.submitEditDocument = function () {

            $http.post('/documents/editDocuments', $scope.document).
                success(function (data, status, headers, config) {
                    for (var i=0; i<$scope.documents.length; i++){
                        if($scope.documents[i].id == data.id){
                            $scope.documents[i] = data;
                            break;
                        }
                    }

                    $scope.editToggleModalShown = !$scope.editToggleModalShown;
                }).
                error(function (data, status, headers, config) {
                    console.log(data);
                });
        }


        $scope.openDatePicker = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();
        };


        $scope.date = '2000-03-12'

    }]);
