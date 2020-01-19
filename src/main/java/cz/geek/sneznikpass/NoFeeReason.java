package cz.geek.sneznikpass;

public enum NoFeeReason {

	/**
	 * nevidomá, osoba, která je považována za závislou na pomoci jiné fyzické osoby podle zákona upravujícího
	 * sociální služby, osoba, která je držitelem průkazu ZTP/P, a její průvodce
	 */
	ZTP,

	/**
	 * hospitalizovaná na území obce ve zdravotnickém zařízení poskytovatele lůžkové péče s výjimkou osoby,
	 * které je poskytována lázeňská léčebně rehabilitační péče
	 */
	HOSPITAL,

	/**
	 * pečující o děti na zotavovací akci nebo jiné podobné akci pro děti podle zákona upravujícího ochranu
	 * veřejného zdraví konaných na území obce
	 */
	CHILD_CARE,

	/**
	 * vykonávající na území obce sezónní
	 * práci pro právnickou nebo podnikající fyzickou
	 * osobu
	 */
	SEASON_WORK,

	/**
	 * pobývající na území obce ve školském zařízení pro výkon ústavní nebo ochranné výchovy anebo školském zařízení
	 * pro preventivně výchovnou péči anebo v zařízení pro děti vyžadující okamžitou pomoc, v zařízení poskytujícím
	 * ubytování podle zákona upravujícího sociální služby,v zařízení sloužícím k pomoci lidem v ohrožení nebo nouzi
	 * provozovaném veřejně prospěšným poplatníkem daně z příjmů právnických osob, nebo za účelem výkonu záchranných
	 * nebo likvidačních prací podle zákona o integrovaném záchranném systému.
	 */
	SOCIAL_WORK,

	/**
	 * Od poplatku z pobytu je osvobozen příslušník bezpečnostního sboru, voják v činné službě, státní zaměstnanec
	 * nebo zaměstnanec České republiky pobývající na území obce v za- řízení ve vlastnictví České republiky
	 * nebo této obce v souvislosti s plněním služebních nebo pracovních úkolů.
	 */
	SOLDIER
}
