<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<!-- // table2excel.js �� ���� ���־�� 
// *��κ��� ���̺귯���� ���ÿ��� �׽� jquery ���Ŀ� ��������  -->
 <script src="../ajax/libs/jquery_2.2.4/jquery.min.js"></script>
<script src="/jquery/jquery.table2excel.js" type="text/javascript"></script>
<!-- <script>
$("#excel").click(function () {
    $("#tableName").table2excel({
        name: "Worksheet Name",
        filename: filename,
        fileext: ".xls"
    });
});
</script> -->
</head>
<body>
 <table id="table1" data-tableName="Test Table 1">
            <thead>
                <tr><td>�Ϲ�</td></tr>
            </thead>
            <tbody>
                <tr>
                    <td>test one</td>
                    <td>test one</td></tr>
                <tr>
                    <td>test two</td>
                    <td>test two</td>
                </tr>
            </tbody>
            
        </table>
 
 
        <script>
            $(function() {
                $("#table1").table2excel({
                    name: "Excel table",
                    filename: "excel table",
                    fileext: ".xls",
                    exclude_img: true,
                    exclude_links: true,
                    exclude_inputs: true
                });
            });
        </script>


</body>
</html>