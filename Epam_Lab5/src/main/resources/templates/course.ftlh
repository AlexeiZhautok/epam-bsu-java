<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LanguageLearningProjectNew</title>
</head>
<body>

<div>
    <div>Add a course</div>
    <form action="/course/addCourse" method="post">
        <label>
            <input type="text" name = "name" placeholder="Имя"/>
        </label>
        <label>
            <input type="text" name = "organization" placeholder="Организация"/>
        </label>
        <button type = "submit">Add</button>
    </form>
    <div>Add user to a course</div>
    <form action="/course/addUserOnCourse" method="get">
        <label>
            <input type="text" name = "name" placeholder="Name"/>
        </label>
        <label>
            <input type="text" name = "login" placeholder="loginOfUser"/>
        </label>
        <button type = "submit">Add</button>
    </form>

    <div>Delete course</div>
    <form method="get" action="/course/delete">
        <label>
            <input type="text" name = "name" placeholder="Название"/>
        </label>
        <button type = "submit">Delete</button>
    </form>

    <div>Remove user from a course</div>
    <form action="/course/removeUserFromCourse" method="get">
        <label>
            <input type="text" name = "name" placeholder="Course name"/>
        </label>
        <label>
            <input type="number" name = "id" placeholder="userID"/>
        </label>
        <button type = "submit">Add</button>
    </form>

    <a href="/">Exit</a>
</div>
<#list courses as course>
    <table>
        <tr>
            <th> id - ${course.getId()},</th>
            <th>${course.getName()}</th>
            <th>${course.getOrganization()}</th>
            <th>Users: <#list courseParticipants as courseParticipant ><#if course.getId() == courseParticipant.getCourseId()>${courseParticipant.getUserId()} </#if> <#else >No users</#list></th>
        </tr>
    </table>
</#list>
</body>
</html>