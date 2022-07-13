<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){

	//확장자에 따른 크기설정 + 검사  <- 웹 공격을 막기위함
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; //5MB

	function checkExtension(fileName, fileSize){

		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
			}

		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드할 수 없습니다");
			return false;
			}

		return true;
		}
			
	
	$("#uploadBtn").on("click", function(e){
		//jquery 파일 업료드는 FomrData 객체 사용(가상의<Form> 태그 를 쓰는것과 같다)하여 필요한
		//파라미터를 담아서 전송
		var formData = new FormData();
		//input태그중 name 이 같은 태그의 값만 넣는다  - 태그선택 방법 $("input")
		var inputFile = $("input[name='uploadFile']");
		//files 자동으로 배열화,  .files로 FileList 배열에 저장된 File 타입을 꺼내온다(인덱스0 에 한번에 넣어둔 상태)
		var files = inputFile[0].files;

		console.log(files);

		//add File Data to formData
		for(var i =0; i < files.length; i++){
			
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
				}
			//인덱스 0부터 있는번호까지 꺼내와서 추가
			//key , value[] 로 뒤에 추가된다 - uploadFile 의 0~leng-1 인덱스까지 있는것
			formData.append("uploadFile", files[i]);
			}

		$.ajax({
			url: '/uploadAjaxAction',
			//process,content false 로 지정해줘야 전송된다
				processData: false,
				contentType: false,
				data: formData,
				type: 'POST',
				success: function(result){
						alert("Uploaded");
					}
			})//end$.ajax
		
		});
});
</script>

<title>ajax 파일업로드</title>
</head>
<body>
<h1>Upload with Ajax</h1>

<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple>
	
</div>

<button id="uploadBtn" >Upload</button>


</body>
</html>