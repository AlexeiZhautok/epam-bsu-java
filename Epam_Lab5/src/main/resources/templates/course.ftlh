<#import "parts/security.ftlh" as s>
<#import "parts/main.ftlh" as m>
<@m.page>
<div>
    <h4 style="color: orange">${message!}</h4>
    <#if s.isAdmin>
    <div>Add a course</div>
    <form action="/course/addCourse" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="input-group mb-3">
            <input type="text" name = "name" class="form-control" placeholder="Course Name" aria-label="Username" aria-describedby="basic-addon2">
            <input  type="text" name = "organization" class="form-control" placeholder="Organization" aria-label="Username" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Add</button>
            </div>
        </div>

    </form>
    <div>Add user to a course</div>
    <form action="/course/addUserOnCourse" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}" >
        <div class="input-group mb-3">
            <input type="text" name = "name" class="form-control" placeholder="Course Name" aria-label="Username" aria-describedby="basic-addon2">
            <input  type="text" name = "login" class="form-control" placeholder="User Login" aria-label="Username" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Add</button>
            </div>
        </div>
    </form>

    <div>Delete course</div>
    <form method="get" action="/course/delete">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="input-group mb-3">
            <input type="text" name = "name" class="form-control" placeholder="Course Name" aria-label="Username" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Delete</button>
            </div>
        </div>
    </form>

    <div>Remove user from a course</div>
    <form action="/course/removeUserFromCourse" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="input-group mb-3">
            <input type="text" name = "name" class="form-control" placeholder="Course Name" aria-label="Username" aria-describedby="basic-addon2">
            <input  type="number" name = "id" class="form-control" placeholder="userID" aria-label="Username" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Delete</button>
            </div>
        </div>
    </form>
    </#if>
</div>
    <div class="container">
        <h2>Courses</h2>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Organization</th>
                <th>ListOfUsers</th>
                 <#if s.isAdmin>
                        <#else >
                            <th>enroll</th>
                            <th>Unroll</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#list courses as course>
                <tr>
                    <th> id - ${course.getId()},</th>
                    <th>${course.getName()}</th>
                    <th>${course.getOrganization()}</th>
                    <th>  <#if course.getUserSet()?? >
                            Users:
                            <#list course.getUserSet() as user >
                                ( ${user.getUsername()};${user.getId()} )
                            <#else >
                                No Users
                            </#list>
                        <#else > Users: No Users
                        </#if>
                    </th>
                    <#if s.isAdmin>
                        <#else >
                            <th>
                                <form action="/course/subscribe" method="get">
                                    <input type="hidden" value="${course.getId()}" name ="courseId"/>
                                    <button class="btn btn-outline-primary" >enroll</button>
                                </form>
                            </th>
                            <th>
                                <form action="/course/unsubscribe" method="get">
                                    <input type="hidden" value="${course.getId()}" name ="courseId"/>
                                    <button class="btn btn-outline-primary" >unenroll</button>
                                </form>
                            </th>
                    </#if>

                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@m.page>