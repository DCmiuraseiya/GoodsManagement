function confirmDelete(event) {
    let result = confirm("本当に削除しますか？");
    if (result) {
        // OKボタンがクリックされた場合の処理
        let id = event.target.getAttribute("ids");
        window.location = "./delete/" + id;
    }
}