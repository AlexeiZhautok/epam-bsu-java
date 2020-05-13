<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LanguageLearningProjectNew</title>
</head>
<body>
<div>
    <div>Add</div>
    <form method="post" action="/user/add">
        <label>
            <input type="text" name = "login" placeholder="Логин"/>
        </label>
        <label>
            <input type="text" name = "password" placeholder="Пароль"/>
        </label>
        <label>
            <input type="text" name = "email" placeholder="Почта"/>
        </label>
        <label>
            <input type="text" name = "role" placeholder="Роль"/>
        </label>
        <button type = "submit">Add</button>

    </form>
    <div>Delete</div>
    <form method="get" action="/user/delete">
        <label>
            <input type="text" name = "login" placeholder="Логин"/>
        </label>
        <button type = "submit">Delete</button>
    </form>
    <div>Find</div>
    <form method="get" action="/user/find">
        <label>
            <input type="text" name = "login" placeholder="Логин"/>
        </label>
        <button type = "submit">find</button>
    </form>

    <a href="/">Exit</a>
</div>
<#list users as user>
    <table>
        <tr>
            <th> id - ${user.getId()},</th>
            <th>${user.getLogin()}</th>
            <th>${user.getPassword()}</th>
            <th>${user.getEmail()}</th>
            <th>${user.getRole()}</th>
        </tr>
    </table>
</#list>
</body>
</html>