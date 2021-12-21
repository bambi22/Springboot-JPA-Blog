let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ // function(){} 대신 ()=>{} this를 바인딩하기 위해서
			this.save();
		});
		$("#btn-update").on("click", ()=>{ // function(){} 대신 ()=>{} this를 바인딩하기 위해서
			this.update();
		});
	},
	
	save: function(){
		//alert('user의 save 호출');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //응답 왔을 시 기본적으로 버퍼에 담겨와서 문자열 
		}).done(function(resp){
			//console.log(resp);
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	update: function(){
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
				
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //응답 왔을 시 기본적으로 버퍼에 담겨와서 문자열 
		}).done(function(resp){
			//console.log(resp);
			alert("회원정보 수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	}
	
}

index.init();