// modifyForm 찾은 후 action = "/movie/remove"
const form = document.querySelector("#modifyForm");

if (form) {
  form.addEventListener("submit", (e) => {
    e.preventDefault();

    if (!confirm("정말로 삭제하시겠습니까?")) {
      return;
    }
    form.action = "/movie/remove";
    form.submit();
  });
}
