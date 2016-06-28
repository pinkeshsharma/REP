<%-- 
    Document   : image
    Created on : Oct 21, 2015, 2:23:46 AM
    Author     : Pinkesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Image</title>
        <script src="script/common_script.js"></script>
    </head>
    <body> 
        <div>
            <h3> Choose Image</h3>
            <form action="uploadImage?action=upload" id="uploadImage" method="post" enctype="multipart/form-data">
                <input type="file" name="file" id="uploadImageInput" required/>
                <input type="submit" onclick="submitImage()" value="upload" />
            </form>          
        </div>
      
    </body>
</html>
