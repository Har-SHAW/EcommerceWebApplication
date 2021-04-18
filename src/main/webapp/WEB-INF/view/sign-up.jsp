<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<style>

body {
    margin: 0px;
}

#navbar {
    background-color: #242129;
    display: flex;
    justify-content: center;
    color: white;
    height: 10vh
    align-items: center;
}

#bodyPart {
    padding-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: white;
    height: 100%;
    display: flex;
    flex-direction: column;
}

#over {
    overflow-x: hidden;
    overflow-y: hidden;
    height: 100vh;
}

#button {
    padding: 10px;
    background-color: #436576;
    border-radius: 10px;
    color: white;
}

#button:hover {
    background-color: white;
    color: #436576;
    cursor: pointer;
}

#button:hover a {
    color: #436576;
}

input {
    padding: 5px;
    border-radius: 5px;
}

#new {
    padding: 15px;
    background-color: #436576;
    color: white;
    display: flex;
    width: 25vw;
    justify-content: center;
    align-items: center;
    font-weight: bold;
    border-radius: 15px;
}

.error {
    color: red;
}


  ::placeholder{
  color: white;
    font-weight: normal;
  }

.inputStyle {
    text-align: center;
    transition: 0.5s;
    width: 225px;
    background: lightgrey;
    padding: 14px 10px;
    outline: none;
    border-radius: 24px;
    border: 1px solid grey;
    color: black;
    font-weight: bold;
 }

 .inputButton {
     text-align: center;
     transition: 0.25s;
     width: 150px;
     padding: 10px;
     outline: none;
     border-radius: 24px;
     border: 1px solid #436576;
     background: #436576;
     color: white;
     font-weight: bold;
  }


  .inputButton:hover {
     background: white;
     color: #436576;
     cursor: pointer;

  }

.inputStyle:focus {
    width: 50vw;
    text-align: center;
    width: 350px;
    background: white;
    color: black;
}

.formStyle {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

</style>
</head>
<body>
<div id="over">
    <div id="navbar">

    <h2>User Registration Page</h2>

    </div>
    <div id="bodyPart">
        <div id="new">Sign Up</div>
        <br>
        <br>
        <br>
        <form:form action="processSignup" modelAttribute="user" cssClass="formStyle">
        <form:input path="username" placeholder="Username" cssClass="inputStyle" autocomplete="off"/>
        <form:errors path="username" cssClass="error" />
        <br>
        <form:input path="password" type="password" placeholder="Password" cssClass="inputStyle" autocomplete="off"/>
        <form:errors path="password" cssClass="error" />
        <br>
        <form:input path="confirmPassword" type="password" placeholder="Confirm Password" cssClass="inputStyle" autocomplete="off"/>
        <form:errors path="confirmPassword" cssClass="error" />
        <br>
        <form:input path="age" placeholder="Age" cssClass="inputStyle" autocomplete="off"/>
        <form:errors path="age" cssClass="error" />
        <br>
        <form:input path="email" placeholder="Email" cssClass="inputStyle" autocomplete="off"/>
        <form:errors path="email" cssClass="error" />
        <br>
        <form:input path="phoneNo" placeholder="Phone Number" cssClass="inputStyle" autocomplete="off"/>
        <form:errors path="phoneNo" cssClass="error" />
        <br>
        <br>
        <input type="submit" class="inputButton"/>
        </form:form>
        <br>
        <br>
        <a href="/"><-- back to home page</a>
        </div>
    </div>
    </div>

</body>
</html>