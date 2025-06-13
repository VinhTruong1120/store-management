document.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:8080/api/manager/lay_staff', {
        method: 'POST',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify({})
    })
    .then(res => {
        if(!res.ok) throw new Error("Lỗi không load được danh sách nhân viên")
        return res.json();
    })
    .then(data => {
        console.log(data)
        
    })
})