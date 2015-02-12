	
	var uploadFileURI = "public/files/";
	var uploadFileName = "";
	
	function submitform()
	{			
		    var inputString = $("#name").val(); 
		    
		    if(inputString == ""){
		    	$("#classifiedResult").html("Input Can`t be empty!");
		    }else{
		    	$.ajax({
			    	url: '/classify',
				    data: JSON.stringify({"inputText": inputString}), 
				    type: 'POST', 
				    contentType: 'application/json',
				    dataType: 'json',
				    success: function(data) {
				    	var resultText = data["tagType"];
				        alert(resultText);
				        $("#classifiedResult").html(inputString+" is a "+resultText);
				        }
			    });	
		    }
	    	    
	}
	
	function submitform2()
	{		
		alert("form2");
		
		var formData = new FormData();
		formData.append('txtFile',  $( '#uploadFile' )[0].files[0]);
		
		if( document.getElementById("uploadFile").files.length == 0 ){
		    console.log("no files selected");
		    alert("No file is Uploaded!");
		}else{
		
		uploadFileName += $( '#uploadFile' )[0].files[0].name;
		
		 if(uploadFileName.lastIndexOf("txt")===uploadFileName.length-3){
			 $("#loaderImage").show();
			 $.ajax( {
			      url: '/Upload/fileUpload',
			      type: 'POST',
			      data: formData,
			      processData: false,
		          contentType: false,
		          cache: false,
			      success: function(result) { 
			    	    $("#loaderImage").hide();
			    	  	$("#showhide2").show();
			            alert("Your file "+result+" is stored Successfuly");
			        }
			    } );
		 }else{
		        alert("Please select a .txt format file only!");
		 }
		 
	   }
				
	}
	
	
	
	function submitform3()
	{
		alert("Form 3");
		$("#loaderImage2").show();
		
		var checked = [];
        $("input[name='tags[]']:checked").each(function ()
        {
            checked.push(parseInt($(this).val()));
        });
		
		alert(checked);
		
		var tagset = "'"+checked+"'";
	    $.ajax({
		    url: '/Summarize/fileSummarize',
		    data: JSON.stringify({"tagset": tagset, "uploadFileURI":uploadFileURI, "fileName":uploadFileName}), 
		    type: 'POST', 
		    contentType: 'application/json',
		    dataType: 'json',
		    success: function(data) {
		    	$("#loaderImage2").hide();
		    	var tag0 = data["tag0"];
		        alert(tag0);
		        $("#showhide").show();
		        }
	    });
	}

