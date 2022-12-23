<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<table id="pet">
<div class="container">
	<h3>애완동물 등록</h3>
	
	<div class="form-group">
		<label for="imagepet">사진:</label> <input type="file"
			 id="imagepet"name="imagepet">
	</div>
	
	
	<div class="form-group">
		<label for="petname">이름:</label> <input type="text"
			class="form-control" id="petname" placeholder="Enter Name"
			name="petname">
	</div>
	
	<div class="form-group">
		<label for="petbirth">생년월일:</label> <input type="date"
			 id="petbirth" name="petbirth">
	</div>
	
	<div class="form-group">
	 <label for="petgender">성별:</label> 
      남<input type="radio" name="gender"  id="petgender" value="남" checked >
  		여<input type="radio" name="gender"  id="petgender" value="여" ><br>
	</div>
	
	<div class="form-group">
			<label for="petweight">몸무게:</label> <input type="text"
				class="form-control" id="petweight" placeholder="Enter weight"
				name="petweight">
		</div>
		
	
	
	<div class="form-group">
	 <label for="petneutering">중성화여부:</label> 
      O<input type="radio" name="petneutering"  id="petneutering" value="O" checked >
  		X<input type="radio" name="petneutering"  id="petneutering" value="X" ><br>
	</div>
	<div class="form-group">
	 <label for="petvaccination">백신여부:</label> 
      O<input type="radio" name="petvaccination"  id="petvaccination" value="O" checked >
  		X<input type="radio" name="petvaccination"  id="petvaccination" value="X" ><br>
	</div>
	<div id="pet">
	품종:
<select name="petbreed" id="petbreed"></select>
<select name="petbreeds"id="petbreeds" ></select>

	</div>
	</table>
	<button type="button" class="btn btn-secondary" id="btnPet">등록</button>

		<script>
		const objTest ={
				소형견: ['말티즈','포메라니안','프렌치 불도그','치와와','비글','요크셔 테리어','닥스훈트'
					,'퍼그','시츄','테리어견','스피츠','푸들','시바견','아키타견','스키퍼키','잉글리시 코커 스패니얼'
					,'라사압소','복서','달마티안','미니어처 핀셔','브뤼셀 그리펀','레케노아','테뷰런','잭 러셀 테리어'
					,'실키 테리어','풍산개','삽살개','보스턴 테리어','티베탄 테리어','토이푸들','미니어처 슈나우저'],//key값 대분류 value값 소분류
				중형견: ['비숑프리제','보더콜리','사모예드','웰시코기','진돗개','차우차우','시베리안 허스키','불 테리어','그레이트 피레니즈','핏불 테리어'],
				대형견: ['래드라도 리트리버','저먼 셰퍼드','골든 리트리버','티베탄 마스티프','체서피크 베이 리트리버','플랫 코티드 리트리버','컬리 코티드 리트리버',
					'도사견','블러드 하운드','불마스티프','마스티프','티베탄 마스티프','말리노이즈','벨지안 십도그','도베르만 핀셔'
					,'자이언트 푸들','캉갈','그레이 하운드','그레이트 데인']
		};
		$(function(){
			init();
			$(document).on('change','select[name=petbreed]',function(){
				const classVal = $(this).val();
			    $('select[name=petbreeds] option').each(function(idx, item) {
			      if ($(this).data('class') == classVal || $(this).val() == '') {
			        $(this).show();
			      } else {
			        $(this).hide();
			      }
			    });
			    $('select[name=petbreeds]').val('');
			})
		});
		//selextbox option  생성
		function init(){
			let petHtml='<option value="">===대분류===</option>';//대분류
			let petsHtml='<option value="">===소분류===</option>';//소분류
			
			for(const key in objTest){
				petHtml += '<option value = '+key+'>'+key+'</option>';
				
				const list = objTest[key];
				
				for(let i=0; i < list.length; i++ ){
					petsHtml += '<option value="'+list[i]+'" data-class='+key+'>'+list[i]+'</option>';
				}
			}
			$('select[name=petbreed]').html(petHtml);
			$('select[name=petbreeds]').html(petsHtml);
			
			$('select[name=petbreeds] option').each(function(idx,item){
				if($(this).val()=='') return true;
				$(this).hide();
			});
		}
		
		$("#btnPet").click(function() {
								
			var dataParam = {
				"petname" : $("#petname").val(),
				"petbirth" : $("#petbirth").val(),
				"petgender" : $("input[name='gender']:checked").val(),
				"petneutering" : $("input[name='petneutering']:checked").val(),
				"petvaccination" :$("input[name='petvaccination']:checked").val(),
				"petweight" : $("#petweight").val(),
				"petbreed" : $("#petbreeds").val(),
				"imagepet" : $("#imagepet").val()
			}
			
			$.ajax({
				
				type : "POST",
				url : "/pet",
				contentType : "application/json;charset=utf-8",
				data : JSON.stringify(dataParam)
			}).done(function(resp) {
				if (resp == "success") {
					alert("등록이 정상적으로 처리되었습니다.")
					location.href="/pet"
				} else if (resp == "fail") {
					alert(".")
					$("#username").val("")
				}
			}) //done
			.fail(function() {
				alert("등록에 실패했습니다.")
			})
		})
		
	</script>
</div>
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>