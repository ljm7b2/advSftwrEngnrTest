angular.module('app.controllers', [])
  
.controller('clinicInfoCtrl', function($scope, $http) {
    $http.get('http://ec2-52-91-251-221.compute-1.amazonaws.com:8080/CliniConnect/GetClinicInfo').then(function(response){
        $scope.clinicAddress = response.data.clinicAddress;
        $scope.clinicPhone = response.data.clinicPhone;
        $scope.clinicName = response.data.clinicName;
        $scope.clinicHours = response.data.clinicHours;
        $scope.clinicEmail = response.data.clinicEmail;
        console.log(response.data);
    
    }, function(error){
    //there was an error fetching from the server
    });
    
    var c = this;
    c.getClinicInfo = function(){
        var ref = cordova.InAppBrowser.open('http://www.mayoclinic.org/', '_blank', 'location=yes');

    }

})
   
.controller('submitVitalsCtrl', function($scope, $http) {
    $scope.sd = "tacos";
    
    $scope.giveResponse = function(resp){
        if(resp == "safe"){
            return "Relax, your levels look great!"
        }
        else{
            return "Your levels seem to be out of balance, please contact your Physician ASAP!"
        }
    }  
    var v = this;
    v.submitVitals = function(){
        console.log(v.BP);
        console.log(v.BS);
    var link = 'http://ec2-52-91-251-221.compute-1.amazonaws.com:8080/CliniConnect/LevelMonitor?' + "BP=" + v.BP + "&" + "BS=" + v.BS;
        
    $http.get(link).then(function (response){
        $scope.status = $scope.giveResponse(response.data.status);
        });
    }
})
    