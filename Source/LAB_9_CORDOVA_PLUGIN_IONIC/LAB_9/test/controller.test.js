describe('vitals control test', function(){
    beforeEach(module('app.controllers'));
    var $controller;
 
    beforeEach(inject(function(_$controller_){
       $controller = _$controller_
    }));
    // tests start here
    describe("submitVitalsCtrl", function(){
        it("test the value success", function(){
            var $scope = {};
            var controller = $controller('submitVitalsCtrl', {$scope: $scope});
            expect($scope.giveResponse("success")).toEqual("Relax, your levels look great!");
            
        });
    });
    
        describe("submitVitalsCtrl", function(){
        it("test the value  fail", function(){
            var $scope = {};
            var controller = $controller('submitVitalsCtrl', {$scope: $scope});
            expect($scope.giveResponse("fail")).toEqual("Your levels seem to be out of balance, please contact your Physician ASAP!");
            
        });
    });
    
        describe("submitVitalsCtrl", function(){
        it("test the value  fail", function(){
            var $scope = {};
            var controller = $controller('submitVitalsCtrl', {$scope: $scope});
            expect($scope.giveResponse("")).toEqual(null);
            
        });
    });
 
});



