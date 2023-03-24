<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../layout.css" rel="stylesheet" type="text/css" />
<style>
A.yim3:link {
	color: 0030ff;
	text-decoration: none;
}

A.yim3:visited {
	color: 0030ff;
	text-decoration: none;
}

A.yim3:active {
	color: 0030ff;
	text-decoration: none;
}

A.yim3:hover {
	color: 0030ff;
	text-decoration: underline
}
</style>

</head>
<body>


	<div style="width: 100%;">
		<div id="top">
			<div style="position: relative; top: 20px;">
				<div style="width: 232px; float: left;">
					<font style="font-family: Arial; color: #3333cc;"><b>Mailing
							Address</b><br> 70, Bubljungang-Ro, 221 Boen-Gil, Bubal-Eub,<br>
						Icheon-Si, Gyuggi-do 467-866 Korea<br> Tel. 82-31-632-1590
						Fax. 82-31-632-1490</font>
				</div>


				<div style="position: relative; left: 150px;">
					<div style="width: 232px; float: left;">
						<div style="position: relative; top: 20px; left: 20px;">
							<a href="../index.php" class=yim3><font
								style="font-family: Arial; font-size: 3em; color: #3333cc;"><b>SESStech</b></font></a>
						</div>
					</div>
				</div>


				<div style="position: relative; left: 330px;">
					<div style="width: 232px; float: left;">
						<?=outlogin("infoedit");?>
					</div>
				</div>

			</div>
		</div>
	</div>


	<div style="width: 100%; background-color: #3333cc;">
		<div id="menu">
			<? include_once ('menu.php')?>
		</div>
	</div>


	<div style="width: 100%;">
		<div id="mimg">
			<div style="position: relative; left: 60px; top: 100px;">
				<font style="font-size: 15px; color: #3333cc; font-family: Arial;">THE
					SESSTECH CO., LTD. IS PERFORMING FOLLOWING OCCUPATIONS FOR THE
					SEMICONDUCTOR FABRICATION EQUIPMENTS.(ESPECIALLY THE USED
					EQUIPMENTS)<br>
				<br> On the other hands we are supplying not only the
					consumable parts and non-consumable parts but also arrange the used
					equipment for trading.<br>
				<br> The main business of SESSTech is that covers the whole of
					Axcelis tools such as (NV-10, NV-6200, NV-8200, NV-GSD,
					NV-GSD/Ultra, NV-GSD/HE, HE3, Paradigm XE).<br>
				<br> For your successful investment SESSTech is performing the
					occupation by special engineers who has over 20 years career, and
					they can solve the problems what is faced on the customer's view
					within the shortest time on site, if it is necessary to solve the
					problem.<br>
				<br> The business region of SESSTech is Korea, China, Japan,
					Taiwan, Singapore & Southeast Asia as well as Europe<br>


				</font><Br>
			</div>

		</div>
	</div>

	<div id="tail">
		<? include_once ('tail.php')?>
	</div>

</body>
</html>