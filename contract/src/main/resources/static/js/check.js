function sendIt() {
    var email = document.f.userEmail.value;
    var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;

    //아이디 입력여부 검사
    if (f.userId.value == "") {
        alert("아이디를 입력하지 않았습니다.")
        f.userId.focus()
        return false;
    }
    //아이디 유효성 검사 (영문소문자, 숫자만 허용)
    for (i = 0; i < document.f.userId.value.length; i++) {
        ch = document.f.userId.value.charAt(i)
        if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
            alert("아이디는 대소문자, 숫자만 입력가능합니다.")
            document.f.userId.focus()
            document.f.userId.select()
            return false;
        }
    }
    //아이디에 공백 사용하지 않기
    if (document.f.userId.value.indexOf(" ") >= 0) {
        alert("아이디에 공백을 사용할 수 없습니다.")
        document.f.userId.focus()
        document.f.userId.select()
        return false;
    }
    //아이디 길이 체크 (4~12자)
    if (document.f.userId.value.length < 4 || document.f.userId.value.length > 12) {
        alert("아이디를 4~12자까지 입력해주세요.")
        document.f.userId.focus()
        document.f.userId.select()
        return false;
    }
    //비밀번호 입력여부 체크
    if (document.f.userPassword.value == "") {
        alert("비밀번호를 입력하지 않았습니다.")
        document.f.userPassword.focus()
        return false;
    }
    if (f.userPassword.value == f.userId.value) {
        alert("아이디와 비밀번호가 같습니다.")
        document.f.userPassword.focus()
        return false;
    }
    //비밀번호 길이 체크(4~8자 까지 허용)
    if (document.f.userPassword.value.length < 4 || document.f.userPassword.value.length > 12) {
        alert("비밀번호를 4~12자까지 입력해주세요.")
        document.f.userPassword.focus()
        document.f.userPassword.select()
        return false;
    }

    //비밀번호와 비밀번호 확인 일치여부 체크
    if (document.f.userPassword.value != document.f.userPasswordck.value) {
        alert("비밀번호가 일치하지 않습니다")
        document.f.userPasswordck.value = ""
        document.f.userPasswordck.focus()
        return false;
    }

    if (document.f.userEmail.value == "") {
        alert("이메일을 입력하지 않았습니다.")
        document.f.userEmail.focus()
        return false;
    }


    if (regex.test(email) === false) {
        alert("잘못된 이메일 형식입니다.");
        document.f.userEmail.value = ""
        document.f.userEmail.focus()
        return false;
    }
    if (document.f.userName.value == "") {
        alert("이름을 입력하지 않았습니다.")
        document.f.userName.focus()
        return false;
    }
    if (document.f.userName.value.length < 2) {
        alert("이름을 2자 이상 입력해주십시오.")
        document.f.userName.focus()
        return false;
    }

    //주소 폰번호 입력여부 체크

    if (document.f.userAddress.value == "") {
        alert("주소를 입력하지 않았습니다.")
        document.f.userAddress.focus()
        return false;
    }

    if (document.f.userPhone.value == "") {
        alert("핸드폰 번호를 입력하지 않았습니다.")
        document.f.userPhone.focus()
        return false;
    }

    document.f.submit()
}

function sendIt2() {
    var email = document.f.kakaoEmail.value;
    var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;


    if (document.f.kakaoEmail.value == "") {
        alert("이메일을 입력하지 않았습니다.")
        document.f.userEmail.focus()
        return false;
    }


    if (regex.test(email) === false) {
        alert("잘못된 이메일 형식입니다.");
        document.f.kakaoEmail.value = ""
        document.f.kakaoEmail.focus()
        return false;
    }
    if (document.f.name.value == "") {
        alert("이름을 입력하지 않았습니다.")
        document.f.name.focus()
        return false;
    }
    if (document.f.name.value.length < 2) {
        alert("이름을 2자 이상 입력해주십시오.")
        document.f.name.focus()
        return false;
    }

    //주소 폰번호 입력여부 체크

    if (document.f.address.value == "") {
        alert("주소를 입력하지 않았습니다.")
        document.f.address.focus()
        return false;
    }

    if (document.f.phoneNumber.value == "") {
        alert("핸드폰 번호를 입력하지 않았습니다.")
        document.f.phoneNumber.focus()
        return false;
    }

    document.f.submit()
}