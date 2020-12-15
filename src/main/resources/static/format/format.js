function alter(elem) {
    const row = elem.parentNode.parentNode.rowIndex - 1;

    const temp = document.createElement("form");
    temp.action = "/alter"
    temp.method = "post"
    temp.style.display = "none"

    const opt = document.createElement("textarea");
    opt.name = "row"
    opt.value = row.toString()
    temp.appendChild(opt)
    document.body.appendChild(temp)

    temp.submit()
    return temp
}

function del(elem) {
    // const row = elem.parentNode.parentNode.rowIndex - 1;
    // const tr = elem.parentNode.parentNode;
    // const tbody = tr.parentNode;
    // tbody.removeChild(tr)
    //
    // const xmlHttpRequest = new XMLHttpRequest();
    // xmlHttpRequest.onreadystatechange = callBack
    // xmlHttpRequest.open("post", "/del", true)
    // xmlHttpRequest.setRequestHeader("Content-Type",
    //     "application/x-www-form-urlencoded")
    // xmlHttpRequest.send("row=" + row)
    //第一次parentNode得到td标签的位置，第二次parentNode得到tr标签的位置
    const row = elem.parentNode.parentNode.rowIndex - 1;
    const tr = elem.parentNode.parentNode;
    const tbody = tr.parentNode;
    const temp = document.createElement("form");
    temp.action = "/del"
    temp.method = "post"
    temp.style.display = "none"
    const opt = document.createElement("textarea");
    opt.name = "row"
    opt.value = row.toString()
    temp.appendChild(opt)
    document.body.appendChild(temp)
    tbody.removeChild(tr)
    temp.submit()
}