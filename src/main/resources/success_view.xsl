<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title><xsl:value-of select="view/header/title"></xsl:value-of></title>
			</head>
			<body>
				<form>
					<xsl:attribute name="name">
						<xsl:value-of select="view/body/form/name"></xsl:value-of>
					</xsl:attribute>
					<xsl:attribute name="action">
						<xsl:value-of select="view/body/form/action"></xsl:value-of>
					</xsl:attribute>
					<xsl:attribute name="method">
						<xsl:value-of select="view/body/form/method"></xsl:value-of>
					</xsl:attribute>
					<table>
						<xsl:for-each select="view/body/form/textView">
							<tr>
								<td>
									<label><xsl:value-of select="label"></xsl:value-of></label>
								</td>
								<td>
									<input type="text">
										<xsl:attribute name="name">
											<xsl:value-of select="name"></xsl:value-of>
										</xsl:attribute>
										<xsl:attribute name="value">
											<xsl:value-of select="value"></xsl:value-of>
										</xsl:attribute>
									</input>
								</td>
							</tr>
						</xsl:for-each>
						<tr>
							<td>
								<label><xsl:value-of select="view/body/form/buttonView/label"></xsl:value-of></label>
							</td>
							<td>
								<input type="submit">
									<xsl:attribute name="name">
										<xsl:value-of select="view/body/form/buttonView/name"></xsl:value-of>
									</xsl:attribute>
								</input>
							</td>
						</tr>
					</table>
				</form>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>