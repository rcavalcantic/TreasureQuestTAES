<?php
  $nome =$_GET['nome'];
  $id_time =$_GET['id_time'];
  $cor = $_GET['cor'];

  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('treasurequest',$conexao);
  
  $sql = "insert into jogadores (nome,id_time,cor) values ('$nome','$id_time','$cor')";
  
  $resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
  if($resultado)
		  echo "true";
   else
          echo "false";
?>
