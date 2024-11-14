//삭제 클릭시 actionForm 전송
//정말로 삭제하시겠습니까?
const actionForm = document.querySelector("#actionForm");

document.querySelector(".btn-danger").addEventListener("click", (e) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    actionForm.action = "/book/remove";
    actionForm.submit();
  }
});

document.querySelector(".btn-secondary").addEventListener("click", (e) => {
  // 목록 클릭 시 a 태그 기능(움직이는) 중지
  e.preventDefault();

  // actionForm 에서 id 요소 제거하기
  actionForm.querySelector("[name='id']").remove("id");

  // actionForm method 는 get 으로 변경
  actionForm.method = "get";

  // actionForm action 은 list 로 변경
  actionForm.action = "/book/list";

  // actionForm submit()
  actionForm.submit();
});
