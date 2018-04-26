function hideLoader(){
	$(".loader").hide();
}

function showLoader(){
	$(".loader").show();
}$(document).ready(function() {
	
	$.ajax({
		url : '/users',
		type: 'GET',
		contentType : 'application/json; charset=utf-8 ',
		success : function(data) {
			var html ="";
			if(data != null){
				html = formTableData(data);
				$("#userTable tbody").html(html);
			}
		},
		error: function() {
			
		}
	});
	
	$(".sendLink").off('click');
	$("body").on('click','.sendLink', function(e) {
		e.preventDefault();
		var sender_id = $(this).attr('data-userid');
		$("#sendModal").modal('show');
		$("#sender_id").val(sender_id);
	});
	
	
	$(".sendLink").off('click');
	$("body").on('click','#sendData', function(e) {
		e.preventDefault();
		var data = {
				senderId : $("#sender_id").val() ,
					recieverId : $("#userList").val() ,
						data : $("#data").val()
		}
		
		$.ajax({
			url : '/encryptData',
			type: 'POST',
			data: JSON.stringify(data),
			contentType : 'application/json; charset=utf-8 ',
			success : function(data) {
				if(data == true) {
					bootbox.alert("Request Sent");
				}else {
					bootbox.alert("Request Fail");
				}
			},
			error: function() {
				
			}
		});
	});
	
	$(".checkDataLink").off('click');
	$("body").on('click','.checkDataLink', function(e) {
		e.preventDefault();
		var data = {
				recieverId : $(this).attr('data-userid')
		}
		$.ajax({
			url : '/checkData',
			type: 'POST',
			data: JSON.stringify(data),
			contentType : 'application/json; charset=utf-8 ',
			success : function(data) {
				var tr = "";
				 $(data).each(function(index) {
					tr += "<tr>";
					tr += "<td class='hcenter'>" + data[index].sender.name + "</td>";
					var accepted = data[index].accepted;
					console.log("accepted" + accepted);
					tr += "<td class='hcenter'><span style='color:"+ (accepted ? "green" : "red") +"'>" + (accepted ? "Accepted" : "Not Accepted") + "</span></td>";
					tr += "<td class='hcenter'><a href='#' data-id="+ data[index].id + " class='btn btn-mini btn-primary requestKey'>Request</a></td>";
					tr += "<td class='hcenter'><button "+ (accepted ? "":"disabled") +" data-id=" +data[index].id+ " class='btn btn-mini btn-danger seeData'>See Data</button></td>";
					tr += "</tr>";
				}); 
				 
				 $("#sender_id").val(sender_id);
				 $("#checkDataTable tbody").html(tr);
				 $("#checkDataModal").modal('show');
				
			},
			error: function() {
				
			}
		});
	});
	
	
	$(".requestKey").off('click');
	$("body").on('click','.requestKey', function(e) {
		e.preventDefault();
		var data = {
				dataId : $(this).attr('data-id')
		}
		$.ajax({
			url : '/requestKey',
			type: 'POST',
			data: JSON.stringify(data),
			contentType : 'application/json; charset=utf-8 ',
			success : function(data) {
				if(data == true) {
					bootbox.alert("Request Sent");
				}else {
					bootbox.alert("Request Fail");
				}
			},
			error: function() {
				
			}
		});
	});
	
	
	$(".checkRequestLink").off('click');
	$("body").on('click','.checkRequestLink', function(e) {
		e.preventDefault();
		var data = {
				senderId : $(this).attr('data-userid')
		}
		$.ajax({
			url : '/requested',
			type: 'POST',
			data: JSON.stringify(data),
			contentType : 'application/json; charset=utf-8 ',
			success : function(data) {
				if(data != null) {
					var tr = "";
					 $(data).each(function(index) {
						tr += "<tr>";
						tr += "<td class='hcenter'>" + data[index].reciever.name + "</td>";
						var accepted = data[index].accepted;
						tr += "<td class='hcenter'><button "+ (accepted ? "disabled" : "") +" data-id=" +data[index].id+ " class='btn btn-mini btn-success accept' value='true'>Accept</button></td>";
						tr += "<td class='hcenter'><button "+ (accepted ? "":"disabled") +" data-id=" +data[index].id+ " class='btn btn-mini btn-danger accept' value='false'>Reject</button></td>";
						tr += "</tr>";
					}); 
				}
				
				 $("#checkRequestedTable tbody").html(tr);
				 $("#checkRequestedModal").modal('show');
			},
			error: function() {
				
			}
		});
	});
	
	
	
	$(".accept").off('click');
	$("body").on('click','.accept', function(e) {
		e.preventDefault();
		var data = {
				accept : $(this).val(),
				dataId : $(this).attr('data-id'),
		}
		$.ajax({
			url : '/accept',
			type: 'POST',
			data: JSON.stringify(data),
			contentType : 'application/json; charset=utf-8 ',
			success : function(data) {
				if(data == true) {
					bootbox.alert("Request Accepted");
				}else {
					bootbox.alert("Request Rejected");
				}
			},
			error: function() {
				
			}
		});
	});

	
	
	$(".seeData").off('click');
	$("body").on('click','.seeData', function(e) {
		e.preventDefault();
		var data = {
				dataId : $(this).attr('data-id'),
		}
		$.ajax({
			url : '/decrypt',
			type: 'POST',
			data: JSON.stringify(data),
			contentType : 'application/json; charset=utf-8 ',
			success : function(data) {
					bootbox.alert(data);
			},
			error: function() {
				
			}
		});
	});
	
});	
	

	function formTableData(data) {
		
		var tr = "";
		var option = "<option></option>";
		 $(data).each(function(index) {
			option += "<option value=" + data[index].id + ">"+ data[index].name +"</option>";
			tr += "<tr>";
			tr += "<td class='hcenter'>" + data[index].name + "</td>";
			tr += '<td class="hcenter"><a href="#" data-userid="'+data[index].id+'" class="btn btn-success sendLink mr10" >Send Data</a>\
									   <a href="#" data-userid="'+data[index].id+'" class="btn btn-danger checkDataLink mr10">Check Data</a>\
									   <a href="#" data-userid="'+data[index].id+'" class="btn btn-info checkRequestLink">Check Request</a>\
									   </td>';
			tr += "</tr>";
		}); 
		 
		 $("#userList").html(option);
		 return tr;
	}