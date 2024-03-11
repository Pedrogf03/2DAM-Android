package com.example.valorantapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.valorantapp.R

data class Agente(

    @StringRes val nombre: Int,
    @DrawableRes val imagen: Int,
    @StringRes val bio: Int,
    val habilidades: List<Habilidad>

)

data class Habilidad(

    @StringRes val nombre: Int,
    @DrawableRes val imagen: Int,
    @StringRes val desc: Int

)

data class Arma(

    @StringRes val nombre: Int,
    @StringRes val desc: Int,
    @DrawableRes val imagen: Int,
    @StringRes val precio: Int

)

object Roles {

    val Duelistas = listOf(

        Agente(
            nombre = R.string.jett,
            imagen = R.drawable.jett,
            bio = R.string.jettBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.jettHab1,
                    imagen = R.drawable.jetthab1,
                    desc = R.string.jettHab1desc
                ), Habilidad(
                    nombre = R.string.jettHab2,
                    imagen = R.drawable.jetthab2,
                    desc = R.string.jettHab2desc
                ), Habilidad(
                    nombre = R.string.jettHab3,
                    imagen = R.drawable.jetthab3,
                    desc = R.string.jettHab3desc
                ), Habilidad(
                    nombre = R.string.jettHab4,
                    imagen = R.drawable.jetthab4,
                    desc = R.string.jettHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.raze,
            imagen = R.drawable.raze,
            bio = R.string.razeBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.razeHab1,
                    imagen = R.drawable.razehab1,
                    desc = R.string.razeHab1desc
                ), Habilidad(
                    nombre = R.string.razeHab2,
                    imagen = R.drawable.razehab2,
                    desc = R.string.razeHab2desc
                ), Habilidad(
                    nombre = R.string.razeHab3,
                    imagen = R.drawable.razehab3,
                    desc = R.string.razeHab3desc
                ), Habilidad(
                    nombre = R.string.razeHab4,
                    imagen = R.drawable.razehab4,
                    desc = R.string.razeHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.phoenix,
            imagen = R.drawable.phoenix,
            bio = R.string.phoenixBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.phoenixHab1,
                    imagen = R.drawable.phoenixhab1,
                    desc = R.string.phoenixHab1desc
                ), Habilidad(
                    nombre = R.string.phoenixHab2,
                    imagen = R.drawable.phoenixhab2,
                    desc = R.string.phoenixHab2desc
                ), Habilidad(
                    nombre = R.string.phoenixHab3,
                    imagen = R.drawable.phoenixhab3,
                    desc = R.string.phoenixHab3desc
                ), Habilidad(
                    nombre = R.string.phoenixHab4,
                    imagen = R.drawable.phoenixhab4,
                    desc = R.string.phoenixHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.reyna,
            imagen = R.drawable.reyna,
            bio = R.string.reynaBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.reynaHab1,
                    imagen = R.drawable.reynahab1,
                    desc = R.string.reynaHab1desc
                ), Habilidad(
                    nombre = R.string.reynaHab2,
                    imagen = R.drawable.reynahab2,
                    desc = R.string.reynaHab2desc
                ), Habilidad(
                    nombre = R.string.reynaHab3,
                    imagen = R.drawable.reynahab3,
                    desc = R.string.reynaHab3desc
                ), Habilidad(
                    nombre = R.string.reynaHab4,
                    imagen = R.drawable.reynahab4,
                    desc = R.string.reynaHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.yoru,
            imagen = R.drawable.yoru,
            bio = R.string.yoruBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.yoruHab1,
                    imagen = R.drawable.yoruhab1,
                    desc = R.string.yoruHab1desc
                ), Habilidad(
                    nombre = R.string.yoruHab2,
                    imagen = R.drawable.yoruhab2,
                    desc = R.string.yoruHab2desc
                ), Habilidad(
                    nombre = R.string.yoruHab3,
                    imagen = R.drawable.yoruhab3,
                    desc = R.string.yoruHab3desc
                ), Habilidad(
                    nombre = R.string.yoruHab4,
                    imagen = R.drawable.yoruhab4,
                    desc = R.string.yoruHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.neon,
            imagen = R.drawable.neon,
            bio = R.string.neonBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.neonHab1,
                    imagen = R.drawable.neonhab1,
                    desc = R.string.neonHab1desc
                ), Habilidad(
                    nombre = R.string.neonHab2,
                    imagen = R.drawable.neonhab2,
                    desc = R.string.neonHab2desc
                ), Habilidad(
                    nombre = R.string.neonHab3,
                    imagen = R.drawable.neonhab3,
                    desc = R.string.neonHab3desc
                ), Habilidad(
                    nombre = R.string.neonHab4,
                    imagen = R.drawable.neonhab4,
                    desc = R.string.neonHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.iso,
            imagen = R.drawable.iso,
            bio = R.string.isoBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.isoHab1,
                    imagen = R.drawable.isohab1,
                    desc = R.string.isoHab1desc
                ), Habilidad(
                    nombre = R.string.isoHab2,
                    imagen = R.drawable.isohab2,
                    desc = R.string.isoHab2desc
                ), Habilidad(
                    nombre = R.string.isoHab3,
                    imagen = R.drawable.isohab3,
                    desc = R.string.isoHab3desc
                ), Habilidad(
                    nombre = R.string.isoHab4,
                    imagen = R.drawable.isohab4,
                    desc = R.string.isoHab4desc
                )
            )
        )

    )

    val Iniciadores = listOf(

        Agente(
            nombre = R.string.breach,
            imagen = R.drawable.breach,
            bio = R.string.breachBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.breachHab1,
                    imagen = R.drawable.breachhab1,
                    desc = R.string.breachHab1desc
                ), Habilidad(
                    nombre = R.string.breachHab2,
                    imagen = R.drawable.breachhab2,
                    desc = R.string.breachHab2desc
                ), Habilidad(
                    nombre = R.string.breachHab3,
                    imagen = R.drawable.breachhab3,
                    desc = R.string.breachHab3desc
                ), Habilidad(
                    nombre = R.string.breachHab4,
                    imagen = R.drawable.breachhab4,
                    desc = R.string.breachHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.sova,
            imagen = R.drawable.sova,
            bio = R.string.sovaBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.sovaHab1,
                    imagen = R.drawable.sovahab1,
                    desc = R.string.sovaHab1desc
                ), Habilidad(
                    nombre = R.string.sovaHab2,
                    imagen = R.drawable.sovahab2,
                    desc = R.string.sovaHab2desc
                ), Habilidad(
                    nombre = R.string.sovaHab3,
                    imagen = R.drawable.sovahab3,
                    desc = R.string.sovaHab3desc
                ), Habilidad(
                    nombre = R.string.sovaHab4,
                    imagen = R.drawable.sovahab4,
                    desc = R.string.sovaHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.skye,
            imagen = R.drawable.skye,
            bio = R.string.skyeBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.skyeHab1,
                    imagen = R.drawable.skyehab1,
                    desc = R.string.skyeHab1desc
                ), Habilidad(
                    nombre = R.string.skyeHab2,
                    imagen = R.drawable.skyehab2,
                    desc = R.string.skyeHab2desc
                ), Habilidad(
                    nombre = R.string.skyeHab3,
                    imagen = R.drawable.skyehab3,
                    desc = R.string.skyeHab3desc
                ), Habilidad(
                    nombre = R.string.skyeHab4,
                    imagen = R.drawable.skyehab4,
                    desc = R.string.skyeHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.kay_o,
            imagen = R.drawable.kay_o,
            bio = R.string.kay_oBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.kay_oHab1,
                    imagen = R.drawable.kayohab1,
                    desc = R.string.kay_oHab1desc
                ), Habilidad(
                    nombre = R.string.kay_oHab2,
                    imagen = R.drawable.kayohab2,
                    desc = R.string.kay_oHab2desc
                ), Habilidad(
                    nombre = R.string.kay_oHab3,
                    imagen = R.drawable.kayohab3,
                    desc = R.string.kay_oHab3desc
                ), Habilidad(
                    nombre = R.string.kay_oHab4,
                    imagen = R.drawable.kayohab4,
                    desc = R.string.kay_oHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.fade,
            imagen = R.drawable.fade,
            bio = R.string.fadeBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.fadeHab1,
                    imagen = R.drawable.fadehab1,
                    desc = R.string.fadeHab1desc
                ), Habilidad(
                    nombre = R.string.fadeHab2,
                    imagen = R.drawable.fadehab2,
                    desc = R.string.fadeHab2desc
                ), Habilidad(
                    nombre = R.string.fadeHab3,
                    imagen = R.drawable.fadehab3,
                    desc = R.string.fadeHab3desc
                ), Habilidad(
                    nombre = R.string.fadeHab4,
                    imagen = R.drawable.fadehab4,
                    desc = R.string.fadeHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.gekko,
            imagen = R.drawable.gekko,
            bio = R.string.gekkoBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.gekkoHab1,
                    imagen = R.drawable.gekkohab1,
                    desc = R.string.gekkoHab1desc
                ), Habilidad(
                    nombre = R.string.gekkoHab2,
                    imagen = R.drawable.gekkohab2,
                    desc = R.string.gekkoHab2desc
                ), Habilidad(
                    nombre = R.string.gekkoHab3,
                    imagen = R.drawable.gekkohab3,
                    desc = R.string.gekkoHab3desc
                ), Habilidad(
                    nombre = R.string.gekkoHab4,
                    imagen = R.drawable.gekkohab4,
                    desc = R.string.gekkoHab4desc
                )
            )
        )

    )

    val Controladores = listOf(

        Agente(
            nombre = R.string.omen,
            imagen = R.drawable.omen,
            bio = R.string.omenBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.omenHab1,
                    imagen = R.drawable.omenhab1,
                    desc = R.string.omenHab1desc
                ), Habilidad(
                    nombre = R.string.omenHab2,
                    imagen = R.drawable.omenhab2,
                    desc = R.string.omenHab2desc
                ), Habilidad(
                    nombre = R.string.omenHab3,
                    imagen = R.drawable.omenhab3,
                    desc = R.string.omenHab3desc
                ), Habilidad(
                    nombre = R.string.omenHab4,
                    imagen = R.drawable.omenhab4,
                    desc = R.string.omenHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.brimstone,
            imagen = R.drawable.brimstone,
            bio = R.string.brimstoneBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.brimstoneHab1,
                    imagen = R.drawable.brimstonehab1,
                    desc = R.string.brimstoneHab1desc
                ), Habilidad(
                    nombre = R.string.brimstoneHab2,
                    imagen = R.drawable.brimstonehab2,
                    desc = R.string.brimstoneHab2desc
                ), Habilidad(
                    nombre = R.string.brimstoneHab3,
                    imagen = R.drawable.brimstonehab3,
                    desc = R.string.brimstoneHab3desc
                ), Habilidad(
                    nombre = R.string.brimstoneHab4,
                    imagen = R.drawable.brimstonehab4,
                    desc = R.string.brimstoneHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.viper,
            imagen = R.drawable.viper,
            bio = R.string.viperBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.viperHab1,
                    imagen = R.drawable.viperhab1,
                    desc = R.string.viperHab1desc
                ), Habilidad(
                    nombre = R.string.viperHab2,
                    imagen = R.drawable.viperhab2,
                    desc = R.string.viperHab2desc
                ), Habilidad(
                    nombre = R.string.viperHab3,
                    imagen = R.drawable.viperhab3,
                    desc = R.string.viperHab3desc
                ), Habilidad(
                    nombre = R.string.viperHab4,
                    imagen = R.drawable.viperhab4,
                    desc = R.string.viperHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.astra,
            imagen = R.drawable.astra,
            bio = R.string.astraBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.astraHab1,
                    imagen = R.drawable.astrahab1,
                    desc = R.string.astraHab1desc
                ), Habilidad(
                    nombre = R.string.astraHab2,
                    imagen = R.drawable.astrahab2,
                    desc = R.string.astraHab2desc
                ), Habilidad(
                    nombre = R.string.astraHab3,
                    imagen = R.drawable.astrahab3,
                    desc = R.string.astraHab3desc
                ), Habilidad(
                    nombre = R.string.astraHab4,
                    imagen = R.drawable.astrahab4,
                    desc = R.string.astraHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.harbor,
            imagen = R.drawable.harbor,
            bio = R.string.harborBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.harborHab1,
                    imagen = R.drawable.harborhab1,
                    desc = R.string.harborHab1desc
                ), Habilidad(
                    nombre = R.string.harborHab2,
                    imagen = R.drawable.harborhab2,
                    desc = R.string.harborHab2desc
                ), Habilidad(
                    nombre = R.string.harborHab3,
                    imagen = R.drawable.harborhab3,
                    desc = R.string.harborHab3desc
                ), Habilidad(
                    nombre = R.string.harborHab4,
                    imagen = R.drawable.harborhab4,
                    desc = R.string.harborHab4desc
                )
            )
        )

    )

    val Centinelas = listOf(

        Agente(
            nombre = R.string.sage,
            imagen = R.drawable.sage,
            bio = R.string.sageBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.sageHab1,
                    imagen = R.drawable.sagehab1,
                    desc = R.string.sageHab1desc
                ), Habilidad(
                    nombre = R.string.sageHab2,
                    imagen = R.drawable.sagehab2,
                    desc = R.string.sageHab2desc
                ), Habilidad(
                    nombre = R.string.sageHab3,
                    imagen = R.drawable.sagehab3,
                    desc = R.string.sageHab3desc
                ), Habilidad(
                    nombre = R.string.sageHab4,
                    imagen = R.drawable.sagehab4,
                    desc = R.string.sageHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.cypher,
            imagen = R.drawable.cypher,
            bio = R.string.cypherBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.cypherHab1,
                    imagen = R.drawable.cypherhab1,
                    desc = R.string.cypherHab1desc
                ), Habilidad(
                    nombre = R.string.cypherHab2,
                    imagen = R.drawable.cypherhab2,
                    desc = R.string.cypherHab2desc
                ), Habilidad(
                    nombre = R.string.cypherHab3,
                    imagen = R.drawable.cypherhab3,
                    desc = R.string.cypherHab3desc
                ), Habilidad(
                    nombre = R.string.cypherHab4,
                    imagen = R.drawable.cypherhab4,
                    desc = R.string.cypherHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.killjoy,
            imagen = R.drawable.killjoy,
            bio = R.string.killjoyBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.killjoyHab1,
                    imagen = R.drawable.killjoyhab1,
                    desc = R.string.killjoyHab1desc
                ), Habilidad(
                    nombre = R.string.killjoyHab2,
                    imagen = R.drawable.killjoyhab2,
                    desc = R.string.killjoyHab2desc
                ), Habilidad(
                    nombre = R.string.killjoyHab3,
                    imagen = R.drawable.killjoyhab3,
                    desc = R.string.killjoyHab3desc
                ), Habilidad(
                    nombre = R.string.killjoyHab4,
                    imagen = R.drawable.killjoyhab4,
                    desc = R.string.killjoyHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.chamber,
            imagen = R.drawable.chamber,
            bio = R.string.chamberBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.chamberHab1,
                    imagen = R.drawable.chamberhab1,
                    desc = R.string.chamberHab1desc
                ), Habilidad(
                    nombre = R.string.chamberHab2,
                    imagen = R.drawable.chamberhab2,
                    desc = R.string.chamberHab2desc
                ), Habilidad(
                    nombre = R.string.chamberHab3,
                    imagen = R.drawable.chamberhab3,
                    desc = R.string.chamberHab3desc
                ), Habilidad(
                    nombre = R.string.chamberHab4,
                    imagen = R.drawable.chamberhab4,
                    desc = R.string.chamberHab4desc
                )
            )
        ),
        Agente(
            nombre = R.string.deadlock,
            imagen = R.drawable.deadlock,
            bio = R.string.deadlockBio,
            habilidades = listOf(
                Habilidad(
                    nombre = R.string.deadlockHab1,
                    imagen = R.drawable.deadlockhab1,
                    desc = R.string.deadlockHab1desc
                ), Habilidad(
                    nombre = R.string.deadlockHab2,
                    imagen = R.drawable.deadlockhab2,
                    desc = R.string.deadlockHab2desc
                ), Habilidad(
                    nombre = R.string.deadlockHab3,
                    imagen = R.drawable.deadlockhab3,
                    desc = R.string.deadlockHab3desc
                ), Habilidad(
                    nombre = R.string.deadlockHab4,
                    imagen = R.drawable.deadlockhab4,
                    desc = R.string.deadlockHab4desc
                )
            )
        )

    )

}

object Armas {

    val ArmasDeMano = listOf(
        Arma(
            nombre = R.string.navaja,
            imagen = R.drawable.navaja,
            desc = R.string.navajaDesc,
            precio = R.string.navajaPrecio
        ),
        Arma(
            nombre = R.string.classic,
            imagen = R.drawable.classic,
            desc = R.string.classicDesc,
            precio = R.string.classicPrecio
        ),
        Arma(
            nombre = R.string.shorty,
            imagen = R.drawable.shorty,
            desc = R.string.shortyDesc,
            precio = R.string.shortyPrecio
        ),
        Arma(
            nombre = R.string.frenzy,
            imagen = R.drawable.frenzy,
            desc = R.string.frenzyDesc,
            precio = R.string.frenzyPrecio
        ),
        Arma(
            nombre = R.string.ghost,
            imagen = R.drawable.ghost,
            desc = R.string.ghostDesc,
            precio = R.string.ghostPrecio
        ),
        Arma(
            nombre = R.string.sheriff,
            imagen = R.drawable.sheriff,
            desc = R.string.sheriffDesc,
            precio = R.string.sheriffPrecio
        )
    )

    val Subfusiles = listOf(
        Arma(
            nombre = R.string.stinger,
            imagen = R.drawable.stinger,
            desc = R.string.stingerDesc,
            precio = R.string.sheriffPrecio
        ),
        Arma(
            nombre = R.string.spectre,
            imagen = R.drawable.spectre,
            desc = R.string.spectreDesc,
            precio = R.string.sheriffPrecio
        )
    )

    val Escopetas = listOf(
        Arma(
            nombre = R.string.bucky,
            imagen = R.drawable.bucky,
            desc = R.string.buckyDesc,
            precio = R.string.buckyPrecio
        ),
        Arma(
            nombre = R.string.judge,
            imagen = R.drawable.judge,
            desc = R.string.judgeDesc,
            precio = R.string.judgePrecio
        )
    )

    val Rifles = listOf(
        Arma(
            nombre = R.string.bulldog,
            imagen = R.drawable.bulldog,
            desc = R.string.bulldogDesc,
            precio = R.string.bulldogPrecio
        ),
        Arma(
            nombre = R.string.guardian,
            imagen = R.drawable.guardian,
            desc = R.string.guardianDesc,
            precio = R.string.guardianPrecio
        ),
        Arma(
            nombre = R.string.phantom,
            imagen = R.drawable.phantom,
            desc = R.string.phantomDesc,
            precio = R.string.phantomPrecio
        ),
        Arma(
            nombre = R.string.vandal,
            imagen = R.drawable.vandal,
            desc = R.string.vandalDesc,
            precio = R.string.vandalPrecio
        )
    )

    val Fusiles = listOf(
        Arma(
            nombre = R.string.marshal,
            imagen = R.drawable.marshal,
            desc = R.string.marshalDesc,
            precio = R.string.marshalPrecio
        ),
        Arma(
            nombre = R.string.operator,
            imagen = R.drawable.operator,
            desc = R.string.operatorDesc,
            precio = R.string.operatorPrecio
        )
    )

    val ArmasPesadas = listOf(
        Arma(
            nombre = R.string.ares,
            imagen = R.drawable.ares,
            desc = R.string.aresDesc,
            precio = R.string.aresPrecio
        ),
        Arma(
            nombre = R.string.odin,
            imagen = R.drawable.odin,
            desc = R.string.odinDesc,
            precio = R.string.odinPrecio
        )
    )

}

