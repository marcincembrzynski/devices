$(document).ready(function(){

    var searchBox = $("input#searchform");

    searchBox.on('keyup paste', function() {
        search(searchBox.val());
    })

    var search = function (query) {
        $.get("/api/devices?query=" + query, function(data, status){
            $("#devices-entries").empty();
            data.result.forEach(addRow);
        });
    }

    var addRow = function(device) {

        row =
            "<tr>" +
            cell(device.uid) +
            cell(device.profile) +
            cell(device.hostname) +
            cell(device.description) +
            cell(device.ipAddress) +
            cell(device.extIpAddress) +
            cell(device.lastUser) +
            cell(device.agentVersion) +
            cell(device.model) +
            cell(device.operatingSystem) +
            cell(device.serialNumber) +
            cell(device.motherboard) +
            cell(device.customField1) +
            cell(device.customField2) +
            cell(device.customField3) +
            cell(device.customField4) +
            cell(device.customField5) +
            "</tr>"
        $("#devices-entries").append(row);
    }

    var cell = function (value) {
        if(value.includes(searchBox.val())) {
            highlighted = value.replace(searchBox.val(), "<span class='highlight'>" + searchBox.val() + "</span>");
            return "<td>" + highlighted + "</td>";
        }else{
            return "<td>" + value + "</td>";
        }
    }

    search("");
});