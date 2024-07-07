package com.builzer.backend.project.adapter.out.persistence

import com.amazonaws.services.route53.model.ConflictingDomainExistsException
import com.builzer.backend.project.adapter.out.persistence.entity.*
import com.builzer.backend.project.adapter.out.persistence.entity.enums.*
import com.builzer.backend.project.adapter.out.persistence.entity.json.ProjectEnv
import com.builzer.backend.project.adapter.out.persistence.repository.*
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import jakarta.persistence.EntityNotFoundException
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import java.math.BigDecimal

@ActiveProfiles("test")
@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProjectJpaRepositoryTest(
        private val projectJpaRepository: ProjectJpaRepository,
        private val projectPlanJpaRepository: ProjectPlanJpaRepository,
        private val supportedDatabaseJpaRepository: SupportedDatabaseJpaRepository,
        private val supportedServerJpaRepository: SupportedServerJpaRepository,
        private val supportedLanguageJpaRepository: SupportedLanguageJpaRepository)
    : BehaviorSpec({

    Given("ProjectJpaEntity 으로") {
        val projectPlanJpaEntity: ProjectPlanJpaEntity = projectPlanJpaRepository
                .findByPlanType(planType = ProjectPlanType.PRO)!!

        var supportedDatabaseJpaEntity = supportedDatabaseJpaRepository.find(
                databaseType = DatabaseType.MYSQL,
                databaseVersion = 8.0F
        )!!

        val supportedLanguageJpaEntity: SupportedLanguageJpaEntity = supportedLanguageJpaRepository.find(
                languageType = LanguageType.JAVA,
                languageVersion = 17F,
                languageProvider = LanguageProvider.OPEN_JDK)!!

        val supportedServerJpaEntity: SupportedServerJpaEntity = supportedServerJpaRepository.find(
                serverName = ServerName.T3_NANO,
                serverType = ServerType.SPOT,
                cloudProvider = CloudProvider.AWS
        )!!

        val savedProjectDetailJpaEntity = ProjectDetailJpaEntity(
                supportedLanguageJpaEntity = supportedLanguageJpaEntity,
                supportedServerJpaEntity = supportedServerJpaEntity,
                supportedDatabaseJpaEntity = supportedDatabaseJpaEntity,
                gitRepositoryBranchName = "main",
                buildRootPath = "/",
                buildTool = BuildTool.GRADLE,
                databaseUsername = "root",
                databasePassword = "12341234",
                isRunTest = true,
                env = listOf(ProjectEnv("ENV_KEY", "1234"))
        )

        val projectJpaEntity = ProjectJpaEntity(
                projectDetailJpaEntities = listOf(savedProjectDetailJpaEntity),
                projectPlanJpaEntity = projectPlanJpaEntity,
                projectName = "test project",
                projectDomainName = "example.builzer.site",
                gitRepositoryName = "test-repo",
                isPrivateGitRepository = false,
        )

        When("save를 하면") {
            val savedProjectJpaEntity = projectJpaRepository
                    .save(projectJpaEntity)

            Then("Project가 생성된다.") {
                savedProjectJpaEntity.id shouldNotBe null
                savedProjectJpaEntity.projectPlanJpaEntity.id shouldBe projectPlanJpaEntity.id
                savedProjectJpaEntity.projectPlanJpaEntity.planType shouldBe projectPlanJpaEntity.planType
                savedProjectJpaEntity.projectPlanJpaEntity.planPrice shouldBe projectPlanJpaEntity.planPrice
                savedProjectJpaEntity.projectPlanJpaEntity.planExplanation shouldBe projectPlanJpaEntity.planExplanation
                savedProjectJpaEntity.projectName shouldBe "test project"
                savedProjectJpaEntity.projectStatus shouldBe ProjectStatus.CREATING
                savedProjectJpaEntity.projectDomainName shouldBe "example.builzer.site"
                savedProjectJpaEntity.gitRepositoryName shouldBe "test-repo"
                savedProjectJpaEntity.isPrivateGitRepository shouldBe false
                savedProjectJpaEntity.projectDetailJpaEntities[0].id shouldNotBe null
                savedProjectJpaEntity.projectDetailJpaEntities[0].gitRepositoryBranchName shouldBe "main"
                savedProjectJpaEntity.projectDetailJpaEntities[0].buildRootPath shouldBe "/"
                savedProjectJpaEntity.projectDetailJpaEntities[0].buildTool shouldBe BuildTool.GRADLE
                savedProjectJpaEntity.projectDetailJpaEntities[0].databaseUsername shouldBe "root"
                savedProjectJpaEntity.projectDetailJpaEntities[0].databasePassword shouldBe "12341234"
                savedProjectJpaEntity.projectDetailJpaEntities[0].isRunTest shouldBe true
                savedProjectJpaEntity.projectDetailJpaEntities[0].env shouldBe listOf(ProjectEnv("ENV_KEY", "1234"))
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedLanguageJpaEntity.id shouldNotBe null
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedLanguageJpaEntity.languageType shouldBe LanguageType.JAVA
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedLanguageJpaEntity.languageVersion shouldBe 17F
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedLanguageJpaEntity.languageProvider shouldBe LanguageProvider.OPEN_JDK
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedServerJpaEntity.id shouldNotBe null
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedServerJpaEntity.serverName shouldBe ServerName.T3_NANO
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedServerJpaEntity.serverType shouldBe ServerType.SPOT
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedServerJpaEntity.cloudProvider shouldBe CloudProvider.AWS
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedDatabaseJpaEntity!!.id shouldNotBe null
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedDatabaseJpaEntity!!.databaseType shouldBe DatabaseType.MYSQL
                savedProjectJpaEntity.projectDetailJpaEntities[0].supportedDatabaseJpaEntity!!.databaseVersion shouldBe 8.0F

            }
        }
    }
})