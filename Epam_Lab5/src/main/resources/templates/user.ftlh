<#import "parts/main.ftlh" as m>
<@m.page>
<div>
    <div>Delete</div>
    <form method="get" action="/user/delete">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="input-group mb-3">
            <input type="text" name = "login"  class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Delete</button>
            </div>
        </div>
    </form>
    <div>Find</div>
    <form method="get" action="/user/find">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="input-group mb-3">
            <input type="text" name = "login"  class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon2">
        </div>
    </form>

</div>

    <table  class="table">
        <thead class="thead-dark">
        <tr>
            <th>id</th>
            <th>Username</th>
            <th>Password</th>
            <th>Roles</th>
            <th>Editor</th>
        </tr>
        </thead>
        <tbody>
            <#list users as user>
                <tr>
                    <th>${user.getId()}</th>
                    <th>${user.getUsername()}</th>
                    <th>${user.getPassword()}</th>
                    <th><div class="col-sm"> <#list user.roles as role>${role} <#sep>, </#list></div></th>
                    <th><div class="col-sm"><a href = "/user/${user.getId()}">edit</a></div></th>
                </tr>
            </#list>
        </tbody>
    </table>

</@m.page>