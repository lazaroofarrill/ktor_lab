<#-- @ftlvariable name="articles" type="kotlin.collections.List<com.example.models.Article>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kotlin Journal</title>
</head>
<body style="text-align: center; font-family: sans-serif">
<img style="width: 200px; object-fit: contain" src="/static/mov.jpg">
<h1>Kotlin Ktor Journal </h1>
<p><i>Powered by Ktor & Freemarker!</i></p>
<hr>
<#list articles?reverse as article>
    <div>
        <h3>
            <a href="/articles/${article.id}">${article.title}</a>
        </h3>
        <p>
            ${article.body}
        </p>
    </div>
</#list>
<hr>
<p>
    <a href="/articles/new">Create article</a>
</p>
</body>
</html>
