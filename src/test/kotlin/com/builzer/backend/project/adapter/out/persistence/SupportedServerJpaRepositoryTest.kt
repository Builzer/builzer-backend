package com.builzer.backend.project.adapter.out.persistence

import com.builzer.backend.project.adapter.out.SupportedServerPersistenceAdaptor
import com.builzer.backend.project.adapter.out.persistence.entity.enums.*
import com.builzer.backend.project.adapter.out.persistence.repository.SupportedServerJpaRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import java.math.BigDecimal

@Import(SupportedServerPersistenceAdaptor::class)
@ActiveProfiles("test")
@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SupportedServerJpaRepositoryTest(private val supportedServerJpaRepository: SupportedServerJpaRepository)
    : BehaviorSpec({

    Given("언어종류, 언어버전, 언어제공자로") {
        val name = ServerName.T3_NANO
        val type = ServerType.SPOT
        val cloudProvider = CloudProvider.AWS

        When("read를 하면") {
            val supportedLanguage = supportedServerJpaRepository
                    .find(serverName = name,
                            serverType = type,
                            cloudProvider = cloudProvider)!!

            Then("해당하는 지원 언어를 가져올 수 있다.") {
                supportedLanguage.id shouldNotBe null
                supportedLanguage.serverName shouldBe name
                supportedLanguage.serverVCpu shouldBe 1
                supportedLanguage.serverMemory shouldBe "1GiB"
                supportedLanguage.serverType shouldBe type
                supportedLanguage.cloudProvider shouldBe cloudProvider
                supportedLanguage.dollarPrice shouldBe BigDecimal.valueOf(100, 2)
                supportedLanguage.creditPrice shouldBe BigDecimal.valueOf(300, 2)
            }
        }
    }
})