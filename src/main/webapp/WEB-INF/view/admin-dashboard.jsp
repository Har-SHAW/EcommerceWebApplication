<html>
<head>
<style>
.body{
margin: 0px;
}
.home{
height: 100%;
width: 100%;
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
text-align: center;
}
.inputButton {
         text-align: center;
         transition: 0.25s;
         padding: 10px 20px;
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
</style>
</head>
<body>
<div class="home">
<div>You have the Admin ROLE</div>
<br>
<br>
<button onclick="location.href='adminDashboard/showUsers'" type="button" class="inputButton">Show Users</button>
<br>
<br>
<button onclick="location.href='/'" type="button" style="background-color: red; color: white; font-weight: bold"><-- Back</button>
</div>
</body>
</html>