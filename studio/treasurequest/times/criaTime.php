<?php
  $nome =$_GET['nome'];
  $id_pergunta_final = $_GET['id_pergunta_final'];

  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('treasurequest',$conexao);
  
  $sql = "insert into times (nome,id_pergunta_final) values ('$nome','$id_pergunta_final')";
  
  $resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
  if($resultado)
		  echo "true";
   else
          echo "false";
?>
