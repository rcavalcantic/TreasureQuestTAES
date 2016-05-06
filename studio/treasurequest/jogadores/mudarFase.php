<?php
  $id_time =$_GET['id_time'];
  $cor = $_GET['cor'];
  $novaFase=$_GET['novaFase'];

  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('treasurequest',$conexao);
  
  $sql = "update jogadores set fase='$novaFase' where id_time='$id_time' and cor='$cor'";
  
  $resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
  if($resultado)
		  echo "true";
   else
          echo "false";
?>
